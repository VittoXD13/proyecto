package com.example.MS_delivery.service;

import com.example.MS_delivery.model.Envio;
import com.example.MS_delivery.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvioService {
    private final EnvioRepository envioRepository;

    public Envio crearEnvio(Long pedidoId, String direccion) {
        Envio envio = new Envio();
        envio.setPedidoId(pedidoId);
        envio.setDireccion(direccion);
        envio.setEstado("PENDIENTE");
        return envioRepository.save(envio);
    }
}
