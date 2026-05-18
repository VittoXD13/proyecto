CREATE TABLE producto (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          stock INT NOT NULL,
                          precio DECIMAL(10, 2) NOT NULL
);

