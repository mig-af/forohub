CREATE TABLE topico (

    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion  VARCHAR(100) NOT NULL,
    status TINYINT(1) NOT NULL,
    curso VARCHAR(255) NOT NULL,
    id_usuario BIGINT,
    FOREIGN KEY(id_usuario) REFERENCES usuarios(id)
   
);