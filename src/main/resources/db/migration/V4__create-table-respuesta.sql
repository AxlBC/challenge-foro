CREATE TABLE Respuesta (

  id bigint not null auto_increment,
  titulo VARCHAR(255) NOT NULL UNIQUE,
  solucion VARCHAR(1000) NOT NULL,
  fecha_creacion TIMESTAMP NOT NULL,
  topico_id bigint NOT NULL,
  usuario_id bigint NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
  FOREIGN KEY (topico_id) REFERENCES Topico(id) ON DELETE CASCADE

);