# Desafío Literatura

Este proyecto es una aplicación que interactúa con la API de Gutendex para buscar y listar libros. La aplicación está desarrollada con Spring Boot y forma parte del Desafío de Alura Cursos Literatura.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.1**
  - spring-boot-starter
  - spring-boot-starter-data-jpa
  - spring-boot-starter-test
- **Jackson Databind** (com.fasterxml.jackson.core:jackson-databind)
- **PostgreSQL** (org.postgresql:postgresql)

## Instalación

1. Clonar el repositorio:

    ```sh
    git clone https://github.com/tu-usuario/desafioliteratura.git
    cd desafioliteratura
    ```

2. Configurar la base de datos PostgreSQL:

    - Crear una base de datos en PostgreSQL.
    - Configurar las credenciales de la base de datos en el archivo `application.properties`:

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contraseña
      ```

3. Compilar y ejecutar la aplicación:

    ```sh
    ./mvnw spring-boot:run
    ```

## Funcionalidades

La aplicación proporciona las siguientes funcionalidades:

1. **Buscar libro**: Permite buscar un libro en la API de Gutendex por título o autor.
2. **Listar libros registrados**: Muestra una lista de todos los libros registrados en la base de datos local.
3. **Listar autores registrados**: Muestra una lista de todos los autores registrados en la base de datos local.
4. **Listar autores vivos en un determinado año**: Permite listar autores que estaban vivos en un año específico.
5. **Listar libros por idioma**: Permite listar libros registrados filtrados por su idioma.

## Uso

Al ejecutar la aplicación, se mostrará un menú con las opciones disponibles:

```plaintext
1 - Buscar libro
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
