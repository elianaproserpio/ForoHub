# 🗣️ ForoHub - API REST con Spring Boot

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen?style=for-the-badge&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![JWT](https://img.shields.io/badge/JWT-Auth0-black?style=for-the-badge&logo=jsonwebtokens)
![Status](https://img.shields.io/badge/Status-Concluido-brightgreen?style=for-the-badge)

## 📖 Descripción

**ForoHub** es una API REST desarrollada como Challenge del programa **Oracle Next Education (ONE)** de Alura Latam. Simula el backend de un foro donde los usuarios pueden crear, listar, actualizar y eliminar tópicos de discusión.

La API implementa autenticación segura con **Spring Security + JWT**, garantizando que solo los usuarios autenticados puedan interactuar con los recursos.

---

## ✨ Funcionalidades

- 🔐 **Autenticación** de usuarios con JWT (JSON Web Token)
- 📝 **Registro** de nuevos tópicos
- 📋 **Listado** de tópicos ordenados por fecha de creación (ASC)
- 🔍 **Detalle** de un tópico específico por ID
- ✏️ **Actualización** de tópicos existentes
- 🗑️ **Eliminación** de tópicos
- 🚫 **Validación** de tópicos duplicados (mismo título y mensaje)

---

## 🚀 Tecnologías utilizadas

| Tecnología | Versión |
|---|---|
| Java | 17 |
| Spring Boot | 3.4.3 |
| Spring Security | 6.4.3 |
| Spring Data JPA | 3.4.3 |
| MySQL | 8.0 |
| Flyway | 10.20.1 |
| JWT (Auth0) | 4.4.0 |
| Lombok | 1.18.34 |
| Maven | Wrapper incluido |

---

## 📁 Estructura del proyecto

```
src/main/java/com/Challenge/demo/
├── Controller/
│   ├── AutenticacionController.java   # Login y generación de token
│   └── TopicoController.java          # CRUD de tópicos
├── domain/
│   ├── topico/
│   │   ├── Topico.java                # Entidad
│   │   ├── TopicoRepository.java      # Repositorio JPA
│   │   ├── DatosRegistroTopico.java   # DTO registro
│   │   ├── DatosActualizarTopico.java # DTO actualización
│   │   └── DatosListadoTopico.java    # DTO listado
│   └── usuario/
│       ├── Usuario.java               # Entidad (implementa UserDetails)
│       ├── UsuarioRepository.java     # Repositorio JPA
│       └── DatosAutenticacionUsuario.java
└── infra/security/
    ├── SecurityConfigurations.java    # Configuración Spring Security
    ├── SecurityFilter.java            # Filtro JWT por request
    ├── TokenService.java              # Generación y validación JWT
    ├── AutenticacionService.java      # UserDetailsService
    └── DatosJWTToken.java             # DTO token
```

---

## ⚙️ Configuración y ejecución

### Prerrequisitos

- Java 17+
- MySQL 8.0+
- IntelliJ IDEA (recomendado)

### 1. Clonar el repositorio

```bash
git clone https://github.com/elianaproserpio/ForoHub.git
cd ForoHub
```

### 2. Configurar `application.properties`

Creá el archivo `src/main/resources/application.properties` basándote en el ejemplo:

```properties
spring.application.name=demo
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_api
spring.datasource.username=TU_USUARIO_MYSQL
spring.datasource.password=TU_CONTRASEÑA_MYSQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
api.security.secret=TU_SECRET_JWT
```

### 3. Crear la base de datos en MySQL

```sql
CREATE DATABASE forohub_api;
```

Flyway ejecutará automáticamente las migraciones al iniciar la app.

### 4. Ejecutar el proyecto

Desde IntelliJ, ejecutá `DemoApplication.java` o desde terminal:

```bash
./mvnw spring-boot:run
```

---

## 🔑 Autenticación

### Login

```http
POST /login
Content-Type: application/json

{
  "login": "usuario@email.com",
  "clave": "tu_contraseña"
}
```

**Respuesta:**
```json
{
  "jwTtoken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Usá el token en todas las demás solicitudes como **Bearer Token** en el header `Authorization`.

---

## 📌 Endpoints

| Método | URI | Descripción | Auth requerida |
|--------|-----|-------------|----------------|
| POST | `/login` | Autenticar usuario | ❌ |
| POST | `/topicos` | Registrar tópico | ✅ |
| GET | `/topicos` | Listar tópicos (10 por fecha ASC) | ✅ |
| GET | `/topicos/{id}` | Detallar tópico | ✅ |
| PUT | `/topicos/{id}` | Actualizar tópico | ✅ |
| DELETE | `/topicos/{id}` | Eliminar tópico | ✅ |

---

## 🧪 Ejemplo de uso con Postman

**1. Registrar un tópico:**
```json
POST /topicos
Authorization: Bearer <token>

{
  "titulo": "Duda sobre Spring Security",
  "mensaje": "¿Cómo configuro el filtro JWT?",
  "autor": "Eliana",
  "curso": "Spring Boot"
}
```

**Respuesta `201 Created`:**
```json
{
  "id": 1,
  "titulo": "Duda sobre Spring Security",
  "mensaje": "¿Cómo configuro el filtro JWT?",
  "fechaCreacion": "2026-03-05T23:00:00",
  "status": "ABIERTO",
  "autor": "Eliana",
  "curso": "Spring Boot"
}
```

---

## 👩‍💻 Autora

**Eliana Proserpio**

Desarrollado como parte del programa **Oracle Next Education (ONE) - Alura Latam**

[![GitHub](https://img.shields.io/badge/GitHub-elianaproserpio-black?style=flat&logo=github)](https://github.com/elianaproserpio)

---

## 📄 Licencia

Este proyecto está bajo la licencia MIT.
