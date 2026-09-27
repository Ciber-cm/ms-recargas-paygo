package com.paygo.ms_recargas.service;
import com.paygo.ms_recargas.client.TarjetaFeignClient;
import com.paygo.ms_recargas.dto.TarjetaDTO;
import com.paygo.ms_recargas.entity.Recarga;
import com.paygo.ms_recargas.repository.RecargaRepository;
import feign.FeignException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@Service
public class RecargaService {
    @Autowired
    public RecargaRepository repo;
    @Autowired private TarjetaFeignClient feign;
    @Autowired private RabbitTemplate rabbitTemplate;

    @Value("${app.queue.name}")
    private String queueName;

    public Recarga registrar(Recarga req) {
        // 1. Validacion SINCRONICA via OpenFeign (Pregunta 1)
        // BUG FIX: antes, si la tarjeta no existia o ms-tarjetas estaba caido,
        // esto lanzaba una FeignException sin capturar y Spring devolvia un 500
        // generico sin explicar la causa. Ahora se devuelve un error claro.
        TarjetaDTO tarjeta;
        try {
            tarjeta = feign.obtenerTarjeta(req.getId_tarjeta());
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "La tarjeta " + req.getId_tarjeta() + " no existe");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "No se pudo conectar con ms-tarjetas: " + e.getMessage());
        }

        req.setSaldo_disponible(tarjeta.getSaldoDisponible());
        req.setFecha_recarga(LocalDateTime.now());

        // 2. Se guarda en la BD de Recargas
        Recarga guardada = repo.save(req);

        // 3. Publicacion ASINCRONICA a la cola para el servicio de Riesgo (Pregunta 2)
        // Se envia justo despues de guardar exitosamente, como pide el enunciado
        rabbitTemplate.convertAndSend(queueName, guardada);

        return guardada;
    }
}
