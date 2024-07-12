CREATE TABLE Usuario (

  id bigint not null auto_increment,
  nombre varchar(255) not null unique,
  correo varchar(255) not null unique,
  clave varchar(255) not null,
  activo BOOLEAN NOT NULL DEFAULT TRUE,

  primary key(id)

);