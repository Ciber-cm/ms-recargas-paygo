package com.paygo.ms_riesgo.dto;

import java.time.LocalDateTime;


public class RecargaMensajeDTO {
    private Long id_recarga;
    private String id_tarjeta;
    private Double saldo_disponible;
    private Double monto_recarga;
    private LocalDateTime fecha_recarga;

    public Long getId_recarga() { return id_recarga; }
    public void setId_recarga(Long id_recarga) { this.id_recarga = id_recarga; }
    public String getId_tarjeta() { return id_tarjeta; }
    public void setId_tarjeta(String id_tarjeta) { this.id_tarjeta = id_tarjeta; }
    public Double getSaldo_disponible() { return saldo_disponible; }
    public void setSaldo_disponible(Double saldo_disponible) { this.saldo_disponible = saldo_disponible; }
    public Double getMonto_recarga() { return monto_recarga; }
    public void setMonto_recarga(Double monto_recarga) { this.monto_recarga = monto_recarga; }
    public LocalDateTime getFecha_recarga() { return fecha_recarga; }
    public void setFecha_recarga(LocalDateTime fecha_recarga) { this.fecha_recarga = fecha_recarga; }
}
