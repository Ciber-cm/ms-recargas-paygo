package com.paygo.ms_riesgo.listener;

import com.paygo.ms_riesgo.entity.Analisis;
import com.paygo.ms_riesgo.repository.AnalisisRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class RiesgoListener {

    @Autowired
    private AnalisisRepository repository;

    @RabbitListener(queues = "Grupo9_Queue")
    public void procesarAnalisis(
            String body,
            @Header(value = "idRecarga", required = false) String idRecarga,
            @Header(value = "idTarjeta", required = false) String idTarjeta,
            @Header(value = "saldoDisponible", required = false) Double saldoDisponible,
            @Header(value = "montoRecarga", required = false) Double montoRecarga
    ) {
        try {
            String r_idRecarga = idRecarga != null ? idRecarga : "REC_TMP";
            String r_idTarjeta = idTarjeta != null ? idTarjeta : "TARJ_TMP";
            Double r_saldo = saldoDisponible != null ? saldoDisponible : 1000.0;
            Double r_monto = montoRecarga != null ? montoRecarga : 100.0;

            Analisis analisis = new Analisis();
            analisis.setIdRecarga(r_idRecarga);
            analisis.setIdTarjeta(r_idTarjeta);
            analisis.setSaldoDisponible(r_saldo);
            analisis.setMontoRecarga(r_monto);
            analisis.setFechaRecarga(LocalDateTime.now());

            double limite = r_saldo * 0.70;
            if (r_monto > limite) {
                analisis.setSituacion("Observada");
            } else {
                analisis.setSituacion("Aprobada");
            }

            repository.save(analisis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
