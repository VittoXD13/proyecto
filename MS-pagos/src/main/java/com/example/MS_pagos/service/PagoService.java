package com.example.MS_pagos.service;

import com.example.MS_pagos.model.Pago;
import com.example.MS_pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;

    // Añade este método dentro de tu clase PagoService
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    public Pago registrarPago(Pago pago) {
        // Lógica de simulación
        pago.setEstado("COMPLETADO");
        pago.setFechaPago(LocalDateTime.now());

        // Aquí podrías añadir validaciones, como chequear que el monto sea > 0
        return pagoRepository.save(pago);
    }

    public List<Pago> obtenerPagosPorPedido(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }
}