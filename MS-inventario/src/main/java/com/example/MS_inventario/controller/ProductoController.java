package com.example.MS_inventario.controller;

import com.example.MS_inventario.model.Producto; // Faltaba esto
import com.example.MS_inventario.service.ProductoService; // Faltaba esto
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Faltaba esto

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public List<Producto> listar() {
        return productoService.listarTodos();
    }

    @PostMapping("/{id}/descontar")
    public ResponseEntity<String> descontar(@PathVariable Long id, @RequestParam Integer cantidad) {
        boolean exito = productoService.validarYDescontarStock(id, cantidad);
        if (exito) {
            return ResponseEntity.ok("Stock actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay stock suficiente");
        }
    }
}