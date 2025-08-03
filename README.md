# Proyecto Lonja 

Sistema de gestión para lonjas: hielo, cajas, barcas y clientes  
**Full stack: Spring Boot (backend) + React (frontend)**

---

## ¿Qué es este proyecto?

Aplicación web para gestionar la entrada y salida de hielo, el registro de cajas devueltas, la gestión de barcas y clientes, y el control de usuarios y roles en una lonja de pescado.

- Gestión de barcas, clientes y devoluciones de cajas
- Registro y resumen de producción de hielo (por barca y cliente)
- Roles diferenciados: **oficina** y **operario**
- Validaciones y autenticación
- Notificaciones por email

---

## Tecnologías usadas

- **Backend:** Java, Spring Boot, JPA, MySQL, JavaMailSender
- **Frontend:** React (Vite), JavaScript, Axios
- **Control de versiones:** Git y GitHub

---

## Estructura del repositorio
proyectoLonja/
│
├── backend/hielo # Spring Boot (API REST, lógica de negocio, JPA)
└── frontend/ # React (interfaz de usuario)


---

## Instalación y despliegue

### 1. Clona el repositorio

```bash
git clone https://github.com/David24977/proyectoLonja.git
cd proyectoLonja
```
---

2. Backend (Spring Boot)
Requisitos: Java 17+, Maven, MySQL

Entra en la carpeta backend/hielo:
```bash
cd backend/hielo
```

Nota:
Copia el archivo application.properties  y rellénalo con tus datos de base de datos y correo antes de arrancar el backend.
Arranca el servidor:
```bash
mvn spring-boot:run
```
---

3. Frontend (React)
Requisitos: Node.js, npm
Entra en la carpeta frontend:
```bash
cd ../frontend
```
Instala dependencias:
```bash
npm install
```
Arranca el frontend:
```bash
npm run dev
```

Abre el navegador en http://localhost:5173

---

 Roles y funcionalidades:

Oficina: Acceso completo, resúmenes, informes, gestión de usuarios/barcas/clientes, edición de precios, etc.

Operario: Acceso a registro diario, devolución de cajas y operaciones básicas.


Autor
David
Técnico informático — GitHub/David24977






