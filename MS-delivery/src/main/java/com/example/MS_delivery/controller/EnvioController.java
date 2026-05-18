package com.example.MS_delivery.controller;

import com.example.MS_delivery.model.Envio;
import com.example.MS_delivery.service.EnvioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class EnvioController {
    private final EnvioService envioService;

    @PostMapping("/crear")
    public Envio prepararEnvio(@RequestParam Long pedidoId, @RequestParam String direccion) {
        return envioService.crearEnvio(pedidoId, direccion);
    }
}
