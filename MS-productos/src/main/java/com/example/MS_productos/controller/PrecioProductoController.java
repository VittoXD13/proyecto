package com.example.MS_productos.controller;

import com.example.MS_productos.model.PrecioProducto;
import com.example.MS_productos.service.PrecioProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/precios")
public class PrecioProductoController {

    @Autowired
    private PrecioProductoService service;


    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<PrecioProducto>> porProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.listarPorProducto(productoId));
    }

    @GetMapping("/producto/{productoId}/sucursal/{sucursalId}")
    public ResponseEntity<List<PrecioProducto>> porProductoYSucursal(
            @PathVariable Long productoId,
            @PathVariable Long sucursalId) {
        return ResponseEntity.ok(service.listarPorProductoYSucursal(productoId, sucursalId));
    }


    @PostMapping
    public ResponseEntity<PrecioProducto> crearPrecio(@RequestBody @Valid PrecioProducto precio) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crearPrecio(precio));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrecio(@PathVariable Long id) {
        service.eliminarPrecio(id);
        return ResponseEntity.noContent().build();
    }
}