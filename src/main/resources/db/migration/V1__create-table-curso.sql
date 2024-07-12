CREATE TABLE Curso (

  id bigint not null auto_increment,
  nombre VARCHAR(255) NOT NULL UNIQUE,
  categoria varchar(255) NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT TRUE,

  primary key(id)

);