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

#### Listado de usuarios y selección específica
![listadoUsuarioActivo](https://github.com/user-attachments/assets/2abeacff-889c-44e7-ac10-7ed97266c68c)
- Permite visualizar una lista completa de los usuarios activos en la base de datos.

![usuarioEspecifico](https://github.com/user-attachments/assets/bb478356-8462-4fd4-9d62-ddb0d37716c4)
- Permite visualizar un usuario específico según su id asignada.

#### Listado de usuarios con sus publicaciones y respuestas
![usuarioEspecificoConListaTopicos](https://github.com/user-attachments/assets/784d929a-c0cc-48d7-8df4-61030b16a14e)
- Muestra a un usuario específico con una lista de tópicos publicados.


![usuarioEspecificoConListaRespuestas](https://github.com/user-attachments/assets/3c513c95-e6b7-4f23-bbee-8872b8f5be7f)
- Muestra a un usuario específico con una lista de respuestas publicadas.

#### Listado de usuarios inactivos
![listadoUsuarioInactivo](https://github.com/user-attachments/assets/1369b338-255f-4303-96bb-5b51806bd2ff)
- Este path permite visualizar las cuentas de los usuarios que se encuentran inactivas en la base de datos.

#### Actualizar usuarios
![actualizarUsuario](https://github.com/user-attachments/assets/ee9d0686-5870-4d34-9cfb-b67d71aab519)
- Actualiza los datos del usuario seleccionado.

#### Eliminar usuarios
![eliminarUsuario](https://github.com/user-attachments/assets/74ed7429-47f9-49a3-ab6b-7f6e180d1afa)
- Al igual que con la tabla curso, este método no borra definitivamente los datos de un usuario de la base de datos, sino que deja su cuenta como inactiva impidiendo que se puedan publicar nuevos tópicos o respuestas con dicha cuenta.


### Tópico

#### Registro de un nuevo tópico
![registrarTopico](https://github.com/user-attachments/assets/4f5f8422-daa4-4477-8739-6108c94e3223)
- Permite registrar un nuevo tópico siempre y cuando tanto el usuario como el curso correspondiente se encuentren activos.

#### Listado de tópicos y seleccion específicas
![listadoDeTopicoYRespuestas](https://github.com/user-attachments/assets/a8043746-91fd-41e9-9efe-9e6127464652)
- Permite visualizar una lista de los tópicos publicados junto a una sub-lista correspondiente a los títulos e id's de las respuestas respectivas a cada tópico.

![listadoDeTopicoEspecífico](https://github.com/user-attachments/assets/febdab28-cbc7-46cd-a7fa-e8aaaeb5c129)
- Permite visualizar los detalles de un tópico en específico según su id en la base de datos.

#### Listados de tópicos resueltos y sin resolver
![listadoDeTopicoResuelto](https://github.com/user-attachments/assets/a7226885-567b-4718-b410-ff2bee2c0192)
- El path ("/resuelto") permite visualizar una lista de los tópicos marcados como "resueltos" a través del uso del valor "false" en la sección de "estado", indicando así que el tópico está ya cerrado debido a que el usuario pudo hallar la solución de su problema.

![listadoDeTopicoSinResolver](https://github.com/user-attachments/assets/9d3b21b5-3b96-481b-b076-b7fd1c573d7b)
- El path ("/sin-resolver") permite visualizar una lista de los tópicos marcados como "sin resolver" a través del uso del valor "true" en la seeción de "estado", indicando así que el usuario aún no encuentra la solución a su problema publicado y el tópico sigue abierto a nuevas respuestas.

#### Actualizar tópico
![actualizaTopico](https://github.com/user-attachments/assets/3d9bb369-7e07-4e20-a45a-fc915d89ed8d)
- Permite actualizar los datos principales de un tópico, así como cambiar el estado de este para comunicar al resto de usuarios que el tópico se encuentra activo o cerrado.

#### Eliminar tópico
![eliminaTopico](https://github.com/user-attachments/assets/9ae0e284-ab01-4129-be56-179c8d8a5f32)
- Permite la eliminación total de un tópico de la base de datos (a diferencia de los registros de cursos y usuarios), incluyendo todas las respuestas relacionadas a dicho tópico elimnado.


### Respuestas

#### Registro de una nueva respuesta
![registroDeRespuesta](https://github.com/user-attachments/assets/4b7daf73-2720-4563-8bbe-356f7f885e0b)
- Permite el registro de una nueva respuesta especificando el id del tópico al que corresponde y el id del usuario que publicará la respuesta.

#### Listado de respuestas y selección específica
![listadoDeRespuesta](https://github.com/user-attachments/assets/87639df9-8965-4510-bda0-6b9da82aaf4f)
- Permite visualizar un listado de las respuestas publicadas por los usuarios.

![listadoDeRespuestaEspecifica](https://github.com/user-attachments/assets/95cdea20-fdb1-453e-9597-58fda36c191e)
- Permite visualizar a detalle una respuesta en específico porsi id en la base de datos.


#### Actualizar respuesta
![actualizaRespuesta](https://github.com/user-attachments/assets/dad7d37b-1dd3-405a-9ea6-64648b9a44ed)
- Permite la actualización de los campos ""título" y "solución" de la respuesta por medio de la id de esta misma.

#### Eliminar respuesta
![eliminacionRespuesta](https://github.com/user-attachments/assets/cc8f5686-1c13-4b96-b621-62c48919ae82)
- Elimina el registro contenedor de la respuesta de la base de datos de forma permanente.

## Tecnologías usadas
- Java 17
- Spring Boot 3.0
- Maven
- Intellij Idea

## Estado del proyecto
El proyecto se encuentra en un estado finalizado y cumpliendo con los requerimientos mínimos establecidos en la propuesta del challenge, sin embargo, aún no se cierra a la posiblidad de obtener pequeñas actualizaciones que traigan consigo algunas mejoras en general.
