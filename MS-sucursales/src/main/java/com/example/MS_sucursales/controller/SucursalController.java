package com.example.MS_sucursales.controller;

import com.example.MS_sucursales.model.Sucursal;
import com.example.MS_sucursales.service.SucursalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;


    @GetMapping
    public ResponseEntity<List<Sucursal>> listarTodas() {
        return ResponseEntity.ok(sucursalService.obtenerTodas());
    }


    @GetMapping("/abiertas")
    public ResponseEntity<List<Sucursal>> listarAbiertas() {
        return ResponseEntity.ok(sucursalService.obtenerAbiertas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sucursalService.obtenerPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Sucursal sucursal) {
        try {
            Sucursal nueva = sucursalService.guardar(sucursal);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody Sucursal datos) {
        try {
            Sucursal existente = sucursalService.obtenerPorId(id);
            existente.setNombre(datos.getNombre());
            existente.setDireccion(datos.getDireccion());
            existente.setLatitud(datos.getLatitud());
            existente.setLongitud(datos.getLongitud());
            existente.setHorarioApertura(datos.getHorarioApertura());
            existente.setHorarioCierre(datos.getHorarioCierre());
            return ResponseEntity.ok(sucursalService.guardar(existente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id,
                                           @RequestParam boolean abierta) {
        try {
            Sucursal sucursal = sucursalService.obtenerPorId(id);
            sucursal.setAbierta(abierta);
            sucursalService.guardar(sucursal);
            String estado = abierta ? "abierta" : "cerrada";
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Sucursal " + estado + " correctamente",
                    "id", id,
                    "abierta", abierta
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            sucursalService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Sucursal eliminada correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }


    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "servicio", "ms-sucursales",
                "estado", "activo",
                "puerto", "9091"
        ));
    }
}