# Web

La página esta corriendo en [http://178.128.216.229:8080/](http://178.128.216.229:8080/)

Se puede correr el proyecto ubicandose en esta carpeta y ejecutando.
```
java -jar build/libs/gs-serving-web-content-0.1.0.jar
```

Para ello se debe tener mongoDB corriendo. Además, por ahora no hay sesiones así que primero se tiene que ir  a http://localhost:9000/example para la creacion de un usuario falso que permitirá el flujo esperado de la página si tuvieramos las sesiones implementada.

## Implementación

### Vistas
- [x] Home
- [x] Login
- [x] Register
- [ ] Error 404

### Api
- [x] Cupones
- [x] Usuarios
- [x] Roles

### Configuración
- [ ] Actualizar favicon
- [ ] Sesiones
- [x] Spring security
- [x] Almacenamiento de imágenes
