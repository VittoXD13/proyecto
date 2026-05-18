CREATE TABLE pago (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      pedido_id BIGINT NOT NULL,
                      monto DECIMAL(10, 2) NOT NULL,
                      metodo_pago VARCHAR(50) NOT NULL,
                      estado VARCHAR(20) NOT NULL,
                      fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Insertamos un pago de prueba para el pedido 1
INSERT INTO pago (pedido_id, monto, metodo_pago, estado)
VALUES (1, 150.00, 'TARJETA', 'COMPLETADO');