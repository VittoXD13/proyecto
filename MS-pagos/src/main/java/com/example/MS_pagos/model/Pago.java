package com.example.MS_pagos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId; // Conexión con MS-PEDIDOS
    private Double monto;
    private String metodoPago; // "TARJETA", "EFECTIVO"
    private String estado; // "COMPLETADO", "RECHAZADO"
    private LocalDateTime fechaPago;
}