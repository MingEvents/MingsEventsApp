# 🎟️ EventManager - Plataforma de Gestión de Eventos

**EventManager** es una plataforma web que permite a administradores crear eventos y a usuarios participar comprando entradas. Además, incluye un sistema de chat individual para comunicación directa, y se conecta a una API para interactuar con la base de datos de forma eficiente y segura.

---

## 🚀 Funcionalidades

### 👥 Roles de Usuario
- **Administrador**:
  - Crear, editar y eliminar eventos.
- **Usuario normal**:
  - Navegar y buscar eventos disponibles.
  - Comprar entradas para eventos.
  - Comunicarse vía chat con administradores.

### 💬 Chat Individual
- Comunicación en tiempo real entre usuarios.
- Implementado mediante un servidor dedicado para manejo de sockets o WebSockets.

### 🌐 API y Base de Datos
- La aplicación se conecta con una API REST para realizar:
  - Registro y autenticación de usuarios.
  - Consultas y operaciones CRUD sobre eventos.
  - Gestión de entradas y transacciones.
---

## 🛠️ Tecnologías Utilizadas

- **Backend**: .Net web api
- **Frontend**: Kotlin / Jetpack Compose
- **Base de datos**: SqlServer
- **API RESTful**
- **WebSockets**: Para el sistema de chat en tiempo real / C#
