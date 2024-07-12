CREATE TABLE Topico (

  id bigint not null auto_increment,
  titulo VARCHAR(255) NOT NULL UNIQUE,
  mensaje VARCHAR(1000) NOT NULL UNIQUE,
  fecha_creacion TIMESTAMP NOT NULL,
  estado BOOLEAN DEFAULT TRUE,
  curso_id bigint NOT NULL,
  usuario_id bigint NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
  FOREIGN KEY (curso_id) REFERENCES Curso(id) ON DELETE CASCADE

);