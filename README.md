# Especificaciones de API para Gestión de Departamentos, Trabajadores y Usuarios

![image](https://github.com/Idliketobealoli/proyectoParaDWEServidor/assets/105634828/8066338a-b95d-4f8e-8c64-50ffcf5b207f)

- API para la gestión de una empresa que cuenta con departamentos, trabajadores y distintos tipos de usuarios (ADMIN, USER).
- Como administrador se pueden crear, modificar y eliminar los departamentos, los trabajadores y los usuarios. Sin embargo, como usuario solo se pueden leer los mismos con la excepción de que cada usuario tiene la posibilidad de actualizar sus propios datos.
- URL base: `http://localhost:8080/company`

## CRUD de Departamentos

### 1. Obtener una lista con todos los departamentos
Permite obtener una lista completa con todos los departamentos que existen.
#### Request
- Método: GET
- URL: `/departments`
#### Respuesta Exitosa
- Código: 200 (OK)
- Contenido: Lista de todos los departamentos
#### Respuestas de Error
- Código: 403 (Forbidden)
  - Descripción: No se tiene acceso a la petición.
- Código: 404 (Not Found)
  - Descripción: El recurso no fue encontrado.

### 2. Obtener un departamento mediante distintos parámetros (ID, Número y Nombre)
Permite obtener toda la información de un departamento mediante su ID.
#### Request
- Método: GET
- URL: `departments/id/{id}`
- URL: `departments/name/{name}`
#### Respuesta Exitosa
- Código: 200 (OK)
- Contenido: Datos del departamento solicitado
#### Respuestas de Error
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para realizar la solicitud.
- Código: 404 (Not Found)
  - Descripción: El departamento solicitado no existe.

### 3. Crear un departamento
Permite crear un nuevo departamento.
#### Request
- Método: POST
- URL: `/departments/create`
#### Respuesta Exitosa
- Código: 201 (Created)
- Contenido: Datos del nuevo departamento creado
#### Respuestas de Error
- Código: 400 (Bad Request)
  - Descripción: El ID del departamento ya está en uso.
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para crear nuevos departamentos.

### 4. Actualizar los datos de un departamento (total y parcialmente)
Permite modificar los datos de un departamento registrado.
#### Request (total)
- Método: PUT
- URL: `/departments/update/{id}`
#### Request (parcial)
- Método: PATCH
- URL: `/departments/patch/{id}`
#### Respuesta Exitosa
- Código: 200 (OK)
- Contenido: Datos del departamento actualizado
#### Respuestas de Error
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para modificar los datos del departamento.
- Código: 404 (Not Found)
  - Descripción: El departamento a actualizar no existe.

### 5. Eliminar un departamento
Permite eliminar un departamento.
#### Request
- Método: DELETE
- URL: `/departments/delete/{id}`
#### Respuesta Exitosa
- Código: 204 (No Content)
#### Respuestas de Error
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para eliminar el departamento.
- Código: 404 (Not Found)
  - Descripción: El departamento a eliminar no existe.

## CRUD de Trabajadores

### 1. Obtener una lista con todos los trabajadores

Permite obtener una lista completa con todos los trabajadores registrados en la empresa.

#### Request

- Método: GET
- URL: `/company/workers`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Lista de todos los trabajadores

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene acceso a la petición.

### 2. Obtener un trabajador mediante distintos parámetros (ID, Nombre)

Permite obtener toda la información de un trabajador mediante su ID o su nombre.

#### Request

- Método: GET
- URL: `/company/workers/{id}`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del trabajador solicitado

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene acceso a la petición.
- Código: 404 (Not Found)
  - Descripción: El trabajador solicitado no existe.

### 3. Crear un trabajador

Permite registrar un nuevo trabajador en la empresa.

#### Request

- Método: POST
- URL: `/company/workers`

#### Respuesta Exitosa

- Código: 201 (Created)
- Contenido: Datos del nuevo trabajador creado

#### Respuestas de Error

- Código: 400 (Bad Request)
  - Descripción: El ID del trabajador ya está en uso.
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para crear nuevos trabajadores.

### 4. Actualizar los datos de un trabajador (total y parcialmente)

Permite modificar los datos de un trabajador registrado.

#### Request

- Método: PUT
- URL: `/company/workers/{id}`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del trabajador actualizado

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para modificar los datos del trabajador.
- Código: 404 (Not Found)
  - Descripción: El trabajador a actualizar no existe.

### 5. Eliminar un trabajador

Permite eliminar un trabajador registrado en la empresa.

#### Request

- Método: DELETE
- URL: `/company/workers/{id}`

#### Respuesta Exitosa

- Código: 204 (No Content)

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para eliminar al trabajador.
- Código: 404 (Not Found)
  - Descripción: El trabajador a eliminar no existe.

## CRUD de Usuarios

### 1. Obtener una lista con todos los usuarios (o todos los que tengan cierto rol)

Permite obtener una lista completa con todos los usuarios registrados en la empresa. Si se proporciona un parámetro "role" a la solicitud, mostrará solo los usuarios que tengan dicho rol.

#### Request

- Método: GET
- URL: `/company/users`
- Parámetros: 
  - Opcional: `role=[rol]` (para filtrar por rol)

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Lista de todos los usuarios (sin mostrar contraseñas)

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene acceso a la petición.

### 2. Obtener un usuario mediante distintos parámetros (ID e Email)

Permite obtener toda la información de un usuario (excepto su contraseña) mediante su ID o su email.

#### Request

- Método: GET
- URL: `/company/users/{id_o_email}`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del usuario solicitado

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene acceso a la petición.
- Código: 404 (Not Found)
  - Descripción: El usuario solicitado no existe.

### 3. Crear un usuario

Permite registrar un nuevo usuario en la empresa, con el rol que considere el administrador que llame a este endpoint.

#### Request

- Método: POST
- URL: `/company/users`

#### Respuesta Exitosa

- Código: 201 (Created)
- Contenido: Datos del nuevo usuario creado y un token de sesión

#### Respuestas de Error

- Código: 400 (Bad Request)
  - Descripción: El ID o el email del nuevo usuario ya está en uso.
- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para crear nuevos usuarios.

### 4. Actualizar los datos de un usuario

Permite modificar los datos de un usuario registrado.

#### Request

- Método: PUT
- URL: `/company/users/{id}`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del usuario actualizado

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para modificar a otros usuarios.
- Código: 404 (Not Found)
  - Descripción: El usuario a actualizar no existe.

### 5. Eliminar un usuario

Permite eliminar un usuario registrado en la empresa.

#### Request

- Método: DELETE
- URL: `/company/users/{id}`

#### Respuesta Exitosa

- Código: 204 (No Content)

#### Respuestas de Error

- Código: 403 (Forbidden)
  - Descripción: No se tiene permiso para eliminar a otros usuarios.
- Código: 404 (Not Found)
  - Descripción: El usuario a eliminar no existe.

### Otras operaciones de Usuarios

#### 1. Actualizar los datos de uno mismo

Permite modificar los datos del mismo usuario que accede a este endpoint. No requiere permisos de administrador, tan solo requiere estar autenticado.

#### Request

- Método: PUT
- URL: `/company/users/me`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del usuario actualizado

#### Respuestas de Error

- Código: 404 (Not Found)
  - Descripción: El usuario solicitado no existe.

#### 2. Registrarse

Permite registrar un nuevo usuario en la empresa, con el rol de USER.

#### Request

- Método: POST
- URL: `/company/users/register`

#### Respuesta Exitosa

- Código: 201 (Created)
- Contenido: Datos del nuevo usuario creado y un token de sesión

#### Respuestas de Error

- Código: 400 (Bad Request)
  - Descripción: El ID o el email del nuevo usuario ya está en uso.

#### 3. Login

Permite autenticarse en el programa.

#### Request

- Método: POST
- URL: `/company/users/login`

#### Respuesta Exitosa

- Código: 200 (OK)
- Contenido: Datos del usuario autenticado y un token de sesión

#### Respuestas de Error

- Código: 404 (Not Found)
  - Descripción: El usuario con el email proporcionado no existe o la contraseña es incorrecta.
