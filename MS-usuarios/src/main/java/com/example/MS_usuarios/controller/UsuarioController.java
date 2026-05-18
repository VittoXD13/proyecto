package com.example.MS_usuarios.controller;

import com.example.MS_usuarios.model.Usuario;
import com.example.MS_usuarios.service.ServiceUsuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final ServiceUsuario serviceUsuario;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            if (serviceUsuario.existsByEmail(usuario.getEmail()))
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Ya existe un usuario con ese email"));

            Usuario guardado = serviceUsuario.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(serviceUsuario.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return serviceUsuario.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Usuario no encontrado con id: " + id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        return serviceUsuario.findByEmail(email)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Usuario no encontrado con email: " + email)));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<Usuario>> buscarPorRol(@PathVariable String rol) {
        return ResponseEntity.ok(serviceUsuario.findByRol(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id,
                                               @Valid @RequestBody Usuario datosNuevos) {
        return serviceUsuario.findById(id)
                .<ResponseEntity<?>>map(existente -> {
                    existente.setNombre(datosNuevos.getNombre());
                    existente.setEmail(datosNuevos.getEmail());
                    existente.setTelefono(datosNuevos.getTelefono());
                    existente.setRol(datosNuevos.getRol());
                    // passwordHash NO se actualiza por aquí — eso es trabajo de MS-seguridad
                    return ResponseEntity.ok(serviceUsuario.save(existente));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Usuario no encontrado con id: " + id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = serviceUsuario.deleteById(id);
        if (eliminado)
            return ResponseEntity.ok(Map.of("mensaje", "Usuario eliminado correctamente"));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Usuario no encontrado con id: " + id));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
                "servicio", "ms-usuarios",
                "estado", "activo",
                "puerto", "9090"
        ));
    }
}