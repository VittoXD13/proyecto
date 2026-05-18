package com.example.MS_inventario.repository;

import com.example.MS_inventario.model.Producto; // Faltaba esto
import org.springframework.data.jpa.repository.JpaRepository; // Faltaba esto
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
