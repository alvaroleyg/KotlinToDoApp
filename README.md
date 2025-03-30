# Aplicación de Gestión de Tareas en Kotlin

Una aplicación de línea de comandos para administrar tareas, desarrollada completamente en Kotlin.

## Características

- **Gestión completa de tareas**: Crear, listar, actualizar y eliminar tareas
- **Priorización**: Asignar nivel de prioridad (Baja, Media, Alta)
- **Fechas límite**: Establecer fechas de vencimiento para tareas
- **Filtros**: Ver tareas por estado (pendientes o completadas) o por prioridad
- **Interfaz intuitiva**: Menú interactivo por consola

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas:

```
KotlinToDoApp/
├── src/
│   ├── Main.kt             # Punto de entrada y UI
│   ├── model/
│   │   └── Task.kt         # Modelo de datos
│   ├── repository/
│   │   └── TaskRepository.kt  # Gestión de almacenamiento
│   └── service/
│       └── TaskService.kt     # Lógica de negocio
```

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **JDK**: Java Development Kit para la ejecución

## Conceptos Demostrados

- Programación orientada a objetos
- Tipos de datos y clases en Kotlin
- Arquitectura en capas
- Gestión de colecciones
- Manejo de fechas
- Interacción con el usuario a través de la consola

## Cómo Compilar y Ejecutar

1. Asegúrate de tener instalado JDK y Kotlin
2. Navega al directorio del proyecto
3. Compila el proyecto con:
   ```
   kotlinc src/model/Task.kt src/repository/TaskRepository.kt src/service/TaskService.kt src/Main.kt -include-runtime -d Main.jar
   ```
4. Ejecuta la aplicación:
   ```
   java -jar Main.jar
   ```

## Uso

1. Al iniciar la aplicación, se muestra un menú con las siguientes opciones:
   - Crear nueva tarea
   - Listar todas las tareas
   - Marcar tarea como completada
   - Actualizar título de tarea
   - Eliminar tarea
   - Filtrar tareas por estado
   - Filtrar tareas por prioridad

2. Para crear una tarea, se solicita:
   - Título
   - Descripción (opcional)
   - Fecha límite (formato yyyy-MM-dd, opcional)
   - Prioridad (Baja, Media, Alta)

3. Cada tarea se guarda con un ID único para su posterior gestión

## Futuras Mejoras

- Persistencia de datos en archivos o base de datos
- Interfaz gráfica de usuario
- Sistema robusto de manejo de errores en entradas del usuario
- Validación avanzada de datos (formatos de fechas, títulos duplicados, etc.)
- Categorías y etiquetas para tareas
- Sistema de búsqueda
- Recordatorios y notificaciones

## Autor

Álvaro Ley (AlleyDev)

---

*Este proyecto fue desarrollado como parte de mi portafolio para demostrar habilidades en Kotlin y conceptos de programación.*
