# Lab 2 Web Server -- Project Report

## Description of Changes
- Se ha creado el fichero `error.html` como plantilla que Spring Boot usará como página de error ante peticiones HTTP.
- Se ha creado un test de integración `SpringBootTest` que verifica que ante una petición HTTP a un endpoint inexistente el servidor devuelve el fichero `error.html` creado previamente.
- Se ha creado el módulo `TimeComponente` que expone un REST endpoint que devuelve la hora actual del servidor. Para ello se ha creado el Data Transfer Object `TimeDTO` para representar y transferir la hora entre la capa de servicio y el cliente, la interfaz `TimeProvider` que deberá implementar el servicio que sirva la hora, el servicio `TimeService`, que es la implementación concreta que obtiene la hora del sistema y la devuelve como `LocalDateTime`, la *extension function* `toDTO` que extiende la clase `LocalDateTime` con un método que encapsula el objeto de la propia clase en el DTO, y la clase `TimeController` que representa un `RestController` que devuelve la serialización del `TimeDTO` ante peticiones `GET` al endpoint `/time`.
- Se ha creado el test de controlador `TimeControllerMVCTests` de tipo `WebMvcTest` que *mockea* el `TimeProvider` con una fecha fija y simula una petición HTTP con `MockMvc` al endpoint `/time` y comprueba que se recibe un estado correcto, contenido de tipo JSON con una clave `time` junto con la fecha establecida.
- Se ha creado un certificado auto-firmado con `openssl` y se ha creado el fichero `application.yml` para configurar el `SSL` y el `HTTP/2`.

## Technical Decisions
- Para el test del `error.html` se ha optado por un test de integración que simula una interacción completa entre cliente y servidor con todo el contexto levantado. La página de error creada depende de la configuración global del `ErrorController` o de la plantilla estática `error.html`, por eso se ha optado por este enfoque. Además, se ha configurado para funcionar sin `SSL` por problemas con el certificado auto-firmado.
- Para el test `TimeControllerMVCTests` se ha optado por utilizar `WebMvcTest` porque tan solo carga la capa web de Spring Boot, sin arrancar todo el contexto de la aplicación. Esto permite perfectamente probar el REST endpoint, pues las dependencias eras pocas (tan solo el `TimeProvider`) que se puede *mockear* fácilmente.


## Learning Outcomes
- El uso de DTO para encapsular datos y transferirlos entre capas de la aplicación o hacia fuera.
- El uso de *extension functions* para añadir nuevas funcionalidades o métodos a clases existentes.
- Los distintos tipos de tests (de integración, de controlador y unitarios), sus implicaciones, usos y posibilidades.

## AI Disclosure
### AI Tools Used
- ChatGPT
- GitHub Copilot

### AI-Assisted Work
- Algunos comentarios han sido generados por GitHub Copilot
- Comprensión del funcionamiento interno de Spring Boot
- Documentación de Kotlin, Spring Boot y otras librerías usadas

### Original Work
- Toda la implementación (código) es propio (y también basado en el código de lab1)
- El aprendizaje se ha centrado principalmente en conocer algunas de las posibilidades que ofrecen los distintos tipos de tests y la estructura de capas de una aplicación Spring Boot.