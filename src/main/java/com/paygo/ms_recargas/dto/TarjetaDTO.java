package com.paygo.ms_recargas.dto;

import lombok.Data;


@Data
public class TarjetaDTO {
    private String idTarjeta;
    private String nomTitular;
    private Double saldoAsignado;
    private Double saldoDisponible;
}
