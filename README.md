# Foro-Hub

## Descripción

Este es el 4.º y último desafío propuesto por el equipo de Alura Latam para concluir la formación de Java con Spring Boot.

El proyecto consiste en la creación de una API REST con la temática de un foro en el que los usuarios podrán publicar 
sus dudas sobre los distintos temas vistos en los diferentes cursos registrados en la plataforma, a su vez, se le permitirá
a otros usuarios visualizar los tópicos publicados por sus compañeros y publicar sus respuestas en dichos tópicos con las que
ayudar a solventar esas dudas.

## Características
- Esta API se centra principalmente en el uso de los tópicos para la resolución de problemas gracias a la ayuda de la comunidad.
- Todos los datos se guardan en una base de datos relacional llamada **foro_db** que permitirá la persistencia de datos.
- La base de datos se conforma de 4 tablas: 
    - **Curso**
    - **Usuario**
    - **Tópico**
    - **Respuesta**
- Permite el uso de las operaciones CRUD: Crear, Leer, Actualizar y Eliminar.
- Implementa validaciones de negocio para comprobar que la información que se quiere ingresar está correctamente estructurada
e impedir valores inválidos o nulos.
- Hace uso de la autenticación JWT (JSON Web Token) para permitir el uso de la API a usuarios que estén registrados en la base
de datos.
- Se implementa la metodología SOLID permitiendo hacer el código más comprensible, legible y mantenible.

## Imágenes de uso

### Cursos


## Tecnologías usadas
- Java 17
- Spring Boot 3.0
- Spring JPA
- Hibernate
- Maven
- Loombok

## Dependencias

## Imágenes 

