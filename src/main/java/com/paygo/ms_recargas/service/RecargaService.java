package com.paygo.ms_recargas.service;
import com.paygo.ms_recargas.client.TarjetaFeignClient;
import com.paygo.ms_recargas.dto.TarjetaDTO;
import com.paygo.ms_recargas.entity.Recarga;
import com.paygo.ms_recargas.repository.RecargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RecargaService {
    @Autowired
    public RecargaRepository repo;
    @Autowired private TarjetaFeignClient feign;

    public Recarga registrar(Recarga req) {
        TarjetaDTO tarjeta = feign.obtenerTarjeta(req.getId_tarjeta());
        req.setSaldo_disponible(tarjeta.getSaldo_disponible());
        req.setFecha_recarga(LocalDateTime.now());
        return repo.save(req);
    }
}
