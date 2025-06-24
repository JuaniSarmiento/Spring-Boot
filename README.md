## Proyecto Integrador: API de Inscripción Universitaria con Spring Boot

Este proyecto es una API REST completamente funcional desarrollada con Spring Boot para gestionar un sistema simple de inscripción de alumnos a materias. La aplicación demuestra una arquitectura por capas, manejo de relaciones complejas en la base de datos, seguridad básica y lógica de negocio.

---

## 🚀 Tecnologías Utilizadas

* **Java 17**: Lenguaje de programación base.
* **Spring Boot**: Framework principal para la creación de la aplicación.
* **Spring Web**: Para la construcción de la API REST y los endpoints.
* **Spring Data JPA**: Para la persistencia de datos y la comunicación con la base de datos.
* **Hibernate**: Implementación de JPA para el Mapeo Objeto-Relacional (ORM).
* **Spring Security**: Para la gestión de autenticación y autorización.
* **H2 Database**: Base de datos en memoria para un entorno de desarrollo rápido.
* **Lombok**: Para reducir el código repetitivo (getters, setters, etc.).
* **Maven**: Como gestor de dependencias y construcción del proyecto.

---

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura de capas bien definida para separar responsabilidades:

com/informaticonfig/spring/app1/integradorspringboot/
│
├── IntegradorSpringBootApplication.java  //&lt;-- Punto de entrada principal
│
├── modelo/
│   ├── Alumno.java
│   └── Materia.java
│
├── repositorio/
│   ├── AlumnoRepository.java
│   └── MateriaRepository.java
│
├── servicio/
│   └── InscripcionService.java
│
└── controlador/
├── AlumnoController.java
├── MateriaController.java
└── InscripcionController.java


### Análisis por Capa

#### 💠 `modelo` (Entidades)
Contiene las clases que representan los datos de nuestro negocio y se mapean a tablas en la base de datos.
* **`Alumno.java`**: Representa a un alumno. Anotada con `@Entity` para ser una tabla y define la parte inversa de la relación con `Materia` (`mappedBy`). Usa `@JsonIgnore` para evitar bucles de serialización.
* **`Materia.java`**: Representa una materia. Anotada con `@Entity` y es la dueña de la relación `@ManyToMany`, configurando la tabla intermedia (`@JoinTable`).

#### 💠 `repositorio` (Acceso a Datos)
Interfaces que hablan directamente con la base de datos, gracias a la magia de Spring Data JPA.
* **`AlumnoRepository` y `MateriaRepository`**: Interfaces que extienden de `JpaRepository`. Nos proveen todos los métodos CRUD (Crear, Leer, Actualizar, Borrar) sin necesidad de escribir una sola línea de SQL. Anotadas con `@Repository`.

#### 💠 `servicio` (Lógica de Negocio)
El cerebro de la aplicación, donde residen las reglas y la orquestación.
* **`InscripcionService.java`**: Contiene la lógica para inscribir un alumno: busca las entidades, valida el cupo y que el alumno no esté previamente inscripto, y persiste el cambio. Anotada con `@Service` y sus métodos con `@Transactional` para asegurar la consistencia de los datos.

#### 💠 `controlador` (Puerta de Entrada)
Expone nuestra lógica al mundo exterior a través de endpoints HTTP.
* **`*Controller.java`**: Clases anotadas con `@RestController`. Reciben las peticiones, extraen datos (`@RequestBody`, `@PathVariable`) y delegan el trabajo a la capa de servicio, devolviendo una respuesta en formato JSON.

---

## Endpoints de la API

A continuación se detallan los endpoints principales de la API.

| Método | URL                                                 | Descripción                                               |
| :----- | :-------------------------------------------------- | :-------------------------------------------------------- |
| `POST` | `/alumnos`                                          | Crea un nuevo alumno.                                     |
| `GET`  | `/alumnos`                                          | Devuelve la lista de todos los alumnos.                   |
| `POST` | `/materias`                                         | Crea una nueva materia.                                   |
| `GET`  | `/materias`                                         | Devuelve la lista de todas las materias.                  |
| `POST` | `/materias/{idMateria}/inscribir/{idAlumno}`         | Inscribe un alumno a una materia. (Endpoint principal).   |
| `GET`  | `/alumnos/{idAlumno}/materias`                       | Devuelve la lista de materias de un alumno específico.    |

---

## ⚙️ Cómo Ejecutar el Proyecto

1.  Clonar el repositorio.
2.  Asegurarse de tener Java 17 y Maven instalados.
3.  Abrir el proyecto con un IDE como IntelliJ IDEA o Eclipse.
4.  Ejecutar la clase principal `IntegradorSpringBootApplication.java`.
5.  La aplicación arrancará en `http://localhost:8080`.
6.  Usar una herramienta como Postman o Insomnia para probar los diferentes endpoints.
