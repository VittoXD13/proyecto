-- 1. Insertar Categorías
INSERT INTO categoria (nombre, descripcion, imagen_url) VALUES
                                                            ('Bebidas', 'Refrescos, jugos y aguas', 'url_imagen_bebidas.jpg'),
                                                            ('Snacks', 'Papas fritas y botanas saladas', 'url_imagen_snacks.jpg'),
                                                            ('Lácteos', 'Leches, quesos y yogures', 'url_imagen_lacteos.jpg');

-- 2. Insertar Productos (usando los IDs de las categorías creadas: 1, 2 y 3)
INSERT INTO producto (categoria_id, nombre, descripcion, imagen_url, unidad_medida, activo) VALUES
                                                                                                (1, 'Coca-Cola 600ml', 'Bebida carbonatada de cola', 'coke.jpg', 'Botella', 1),
                                                                                                (2, 'Papas Originales', 'Bolsa de papas fritas con sal', 'papas.jpg', 'Gramos', 1),
                                                                                                (3, 'Leche Entera 1L', 'Leche de vaca pasteurizada', 'leche.jpg', 'Litro', 1);

-- 3. Insertar Precios (usando los IDs de los productos: 1, 2 y 3)
-- Nota: sucursal_id lo he puesto como 1 para este ejemplo
INSERT INTO precio_producto (producto_id, sucursal_id, precio) VALUES
                                                                   (1, 1, 15.50),
                                                                   (2, 1, 22.00),
                                                                   (3, 1, 28.50);