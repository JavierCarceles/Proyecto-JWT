Esta es una aplicación web que implementa autenticación basada en JWT (JSON Web Tokens) utilizando un backend en Spring Boot y un frontend en React. Permite a los usuarios registrarse, iniciar sesión y acceder a contenido protegido mediante tokens JWT, con soporte multilenguaje en la interfaz.


===========================
====Tecnologías Backend====
===========================

Spring Dependencies:
->Spring Boot 3.5.5: Framework principal para desarrollo de aplicaciones Java.
->Spring Data JPA: Para persistencia de datos y manejo de bases de datos relacionales de forma sencilla.
->Spring Security: Gestión de autenticación y autorización.
->Spring Validation: Para validar DTOs y datos de entrada.

Base de datos:
->MySQL: Base de datos relacional para persistir usuarios y tokens.

Autenticación / Seguridad:
->JWT (io.jsonwebtoken / JJWT): Emisión y validación de tokens JWT.
->Spring Security Test: Para pruebas de endpoints protegidos.

Mapeo y utilidades:
->MapStruct: Conversión entre entidades y DTOs de forma automática.
->Lombok: Reducción de boilerplate en Java (getters, setters, constructores).

Compilación y construcción:
-Maven: Gestión de dependencias y compilación.
-Java 17: Lenguaje base del proyecto.

Arquitectura (1 backend de cada):
->Layered Architecture: Separación típica en controladores → servicios → repositorios → base de datos.
->Hexagonal / Ports & Adapters: Separación de lógica de negocio de infraestructura


============================
====Tecnologías Frontend====
============================

Framework / Librerías:
-> React 18: Biblioteca principal para construir interfaces de usuario reactivas y componentizadas.
-> React DOM: Para renderizar componentes de React en el DOM del navegador.
-> React Router DOM: Gestión de rutas y navegación en la aplicación de manera declarativa.

Compilación y construcción:
-> React Scripts (CRA): Herramienta de construcción, desarrollo y empaquetado proporcionada por Create React App.
-> Browserslist: Configuración de compatibilidad con distintos navegadores en producción y desarrollo.

Autenticación / Comunicación con backend:
-> JWT: Manejo de tokens para autenticación de usuarios, consumiendo endpoints del backend.
-> Fetch / Axios (opcional): Para realizar llamadas HTTP a la API del backend.

Arquitectura / Estructura:
-> Componentes Funcionales y Estado Local: Organización en componentes reutilizables y manejo de estado mediante useState y useEffect.
-> Separación por Funcionalidad: Carpeta de páginas, componentes, servicios (API calls) y utilidades, manteniendo el frontend modular y mantenible.
