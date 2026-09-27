package com.paygo.ms_riesgo.listener;

import com.paygo.ms_riesgo.dto.RecargaMensajeDTO;
import com.paygo.ms_riesgo.entity.Analisis;
import com.paygo.ms_riesgo.repository.AnalisisRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RiesgoListener {

    @Autowired
    private AnalisisRepository repository;


    @RabbitListener(queues = "${app.queue.name}")
    public void procesarAnalisis(RecargaMensajeDTO mensaje) {
        Analisis analisis = new Analisis();
        analisis.setIdRecarga(String.valueOf(mensaje.getId_recarga()));
        analisis.setIdTarjeta(mensaje.getId_tarjeta());
        analisis.setSaldoDisponible(mensaje.getSaldo_disponible());
        analisis.setMontoRecarga(mensaje.getMonto_recarga());
        analisis.setFechaRecarga(mensaje.getFecha_recarga());


        double limite = mensaje.getSaldo_disponible() * 0.70;
        if (mensaje.getMonto_recarga() > limite) {
            analisis.setSituacion("Observada");
        } else {
            analisis.setSituacion("Aprobada");
        }

        repository.save(analisis);
    }
}
