package com.example.MS_productos.repository;

import com.example.MS_productos.model.PrecioProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecioProductoRepository extends JpaRepository<PrecioProducto, Long> {


    List<PrecioProducto> findByProductoId(Long productoId);


    List<PrecioProducto> findByProductoIdAndSucursalId(Long productoId, Long sucursalId);
}