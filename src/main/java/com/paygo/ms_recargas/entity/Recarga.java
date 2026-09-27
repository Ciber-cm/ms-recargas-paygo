package com.paygo.ms_recargas.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "recargas")
@Data
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_recarga;
    private String id_tarjeta;
    private Double saldo_disponible;
    private Double monto_recarga;
    private LocalDateTime fecha_recarga;
}
