CREATE TABLE Usuario (

  id bigint not null auto_increment,
  nombre varchar(255) not null unique,
  correo varchar(255) not null unique,
  contrasena varchar(255) not null,

  primary key(id)

);