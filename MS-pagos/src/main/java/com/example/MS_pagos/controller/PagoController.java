package com.example.MS_pagos.controller;

import com.example.MS_pagos.model.Pago;
import com.example.MS_pagos.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> listarTodos() {
        return ResponseEntity.ok(pagoService.obtenerTodos());
    }

    @PostMapping("/procesar")
    public ResponseEntity<Pago> procesarPago(@RequestBody Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.registrarPago(pago));
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<Pago>> obtenerPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pagoService.obtenerPagosPorPedido(pedidoId));
    }
}
