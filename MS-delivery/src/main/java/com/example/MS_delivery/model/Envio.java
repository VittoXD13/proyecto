package com.example.MS_delivery.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;
    private String direccion;
    private String estado; // "PENDIENTE", "EN_CAMINO", "ENTREGADO"
}
