CREATE TABLE envio (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       pedido_id BIGINT NOT NULL,
                       direccion VARCHAR(255) NOT NULL,
                       estado VARCHAR(50) DEFAULT 'PENDIENTE'
);

-- 4. Insertar unos datos de prueba para que no salga vacía
INSERT INTO envio (pedido_id, direccion, estado) VALUES (101, 'Calle Principal 123', 'PENDIENTE');
INSERT INTO envio (pedido_id, direccion, estado) VALUES (102, 'Avenida Siempre Viva 742', 'EN_CAMINO');
INSERT INTO envio (pedido_id, direccion, estado) VALUES (103, 'Pasaje Los Olivos 45', 'ENTREGADO');