# devsu-prueba

Este proyecto contiene dos microservicios desarrollados con Spring Boot, Java 17 y H2 Database, que interactúan mediante RabbitMQ.

## Descripción de los Microservicios

*   **`ms-cliente-persona`**: Microservicio encargado de la gestión de clientes y personas.
*   **`ms-cuenta-movimiento`**: Microservicio encargado de la gestión de cuentas bancarias y movimientos asociados.

Ambos microservicios utilizan H2 Database para persistencia de datos y RabbitMQ para comunicación asíncrona.

## Tecnologías Utilizadas

*   **Java 17**
*   **Spring Boot**
*   **Maven**
*   **H2 Database** (base de datos en memoria/archivo)
*   **RabbitMQ** (para mensajería asíncrona)
*   **Docker**
*   **Docker Compose**
*   **Springdoc OpenAPI UI** (para documentación Swagger)

## Estructura del Proyecto

El proyecto está organizado en un monorepo que contiene los siguientes directorios principales:

*   `ms-cliente-persona/`: Contiene el código fuente del microservicio de gestión de clientes.
*   `ms-cuenta-movimiento/`: Contiene el código fuente del microservicio de gestión de cuentas y movimientos.
*   `docker-compose.yml`: Archivo de configuración para desplegar todos los servicios con Docker Compose.

## Despliegue con Docker Compose

Para desplegar y ejecutar todos los microservicios junto con RabbitMQ utilizando Docker Compose, sigue los siguientes pasos:

### Prerrequisitos

Asegúrate de tener Docker y Docker Compose instalados en tu sistema.

### Pasos para el Despliegue

1.  **Clonar el Repositorio:**
    Si aún no lo has hecho, clona este repositorio en tu máquina local:
    ```bash
    git clone https://github.com/JuanPabloOrtizJaimes/devsu-prueba.git
    cd devsu-prueba
    ```

2.  **Construir y Levantar los Servicios:**
    Desde el directorio raíz del proyecto (`devsu-prueba`), ejecuta el siguiente comando para construir las imágenes de Docker y levantar todos los contenedores:
    ```bash
    sudo docker compose up --build
    ```
    Este comando construirá las imágenes de los microservicios (si no existen o si hay cambios) y luego iniciará los contenedores para RabbitMQ, `ms-cliente-persona` y `ms-cuenta-movimiento`.

### Acceso a las Aplicaciones y Servicios

Una vez que los contenedores estén en ejecución, podrás acceder a los servicios a través de los siguientes puertos en tu máquina local (o la IP pública si estás en un entorno como Play with Docker):

*   **`ms-cliente-persona`**:
    *   API REST: `http://localhost:8080/api/clientes/v1`
    *   Swagger UI: `http://localhost:8080/api/clientes/v1/swagger-ui.html`
    *   H2 Console: `http://localhost:8080/api/clientes/v1/h2-console`
        *   **Credenciales:** `username=sa`, `password=` (vacío)
        *   **JDBC URL:** `jdbc:h2:file:./data/clientesdb`
        

*   **`ms-cuenta-movimiento`**:
    *   API REST: `http://localhost:8081/api/cuentas/v1`
    *   Swagger UI: `http://localhost:8081/api/cuentas/v1/swagger-ui.html`
    *   H2 Console: `http://localhost:8081/api/cuentas/v1/h2-console`
        *   **Credenciales:** `username=sa`, `password=` (vacío)
        *   **JDBC URL:** `jdbc:h2:file:./data/cuentasdb`
        

*   **RabbitMQ Management UI**:
    *   Interfaz web: `http://localhost:15672`
    *   Credenciales por defecto: `guest`/`guest`

### Colecciones de Postman

Se proporcionan colecciones de Postman para facilitar las pruebas de las APIs de los microservicios. Los archivos de colección se encuentran en la raíz del proyecto:

*   `ms-cliente-persona.postman_collection.json`
*   `ms-cuenta-movimiento.postman_collection.json`

Importa estas colecciones en tu aplicación Postman para comenzar a interactuar con los microservicios.

### Detener los Servicios

Para detener y eliminar todos los contenedores, redes y volúmenes creados por Docker Compose, ejecuta:
```bash
sudo docker compose down
```