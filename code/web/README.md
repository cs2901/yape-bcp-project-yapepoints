# Web

La página está corriendo en [http://142.93.160.192/](http://142.93.160.192/)
hasta fines de Julio del 2019.

Se puede correr el proyecto ubicandose en esta carpeta y ejecutando.
```
java -jar build/libs/gs-serving-web-content-0.1.0.jar
```

Para ello **se debe tener mongoDB ejecutandose**. Además, por ahora no hay sesiones así que primero se tiene que ir  a http://localhost:9000/example para la creacion de un usuario falso que permitirá el flujo esperado de la página si tuvieramos las sesiones implementada.

## Implementación

### Vistas
- [x] Index
- [x] Login
- [x] Recover Password
- [x] Register
- [x] Register Local
- [x] Home
- [x] Edit Coupon
- [x] Add Coupon
- [x] Update information
- [x] Change password
- [x] Error 404

### Main Features
- [x] Sessions (allow login and logout)
- [x] Select address in a map
- [x] Send a secret code as a mail
- [x] CRUD coupons
- [x] Upload images
- [x] JSON with all the available coupons
- [x] QR Codes

