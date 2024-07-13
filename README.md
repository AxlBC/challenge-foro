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

### Registro de usuario
![registroDeUsuario](https://github.com/user-attachments/assets/361114ab-8015-4f99-929c-7b2bd012e50d)

### Login y generación de un JWT (JSON web token)
![loginExitoso_JWTgenerado](https://github.com/user-attachments/assets/a010f764-d725-44c9-b257-e77d517dedc7)


### Curso (Operaciones CRUD)

#### Registro de un nuevo curso
![registroDeNuevoCurso](https://github.com/user-attachments/assets/ec2adcbf-08ac-414c-abe3-9d8f8d91f63b)
- La sección categoría hace uso de un Enum ya establecido en la API.
- No permite otro registro con exactamente el mismo nombre.

#### Listado de los cursos disponibles y selección específica
![listadoDeCursos](https://github.com/user-attachments/assets/2494d93a-23ea-418d-868b-281b068ea320)
![listadoDeCursoEspecifico](https://github.com/user-attachments/assets/57165d50-6c8d-45c8-87b0-c3b53304228c)
- Lista todos los cursos que se encuentran activos.

#### Actualización de cursos
![actualizacionDeUnCurso](https://github.com/user-attachments/assets/16073e93-e0a6-4db8-bb28-ca9ce4e79acb)
- Permite el cambio de título y categoría.

#### Eliminación de cursos 
![eliminacionDeUnCurso](https://github.com/user-attachments/assets/bb825e00-4cb4-4ec0-a0e2-43ee699b5c80)
- La eliminación de cursos los coloca como inactivos en la base de datos.
En un curso inactivo no se permitira la publicación de nuevos tópicos.

#### Visualización de cursos inactivos
![listadoDeCursoEspecifico](https://github.com/user-attachments/assets/04923345-2eeb-45a2-9209-6bc8b0d23647)
- Con el path ("/curso/inactivo") será posible visualizar aquellos cursos que se encuentren inactivos.

### Usuario

### Tópico

### Respuestas

## Tecnologías usadas
- Java 17
- Spring Boot 3.0
- Spring JPA
- Hibernate
- Maven
- Loombok

## Dependencias

## Imágenes 

