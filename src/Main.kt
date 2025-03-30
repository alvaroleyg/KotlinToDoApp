import model.Priority
import repository.TaskRepository
import service.TaskService
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun main() {
    val taskRepository = TaskRepository()
    val taskService = TaskService(taskRepository)
    
    println("¡Bienvenido a la aplicación de tareas en Kotlin!")
    
    var running = true
    while (running) {
        printMenu()
        when (readLine()?.toIntOrNull()) {
            1 -> createNewTask(taskService)
            2 -> listAllTasks(taskService)
            3 -> markTaskAsCompleted(taskService)
            4 -> updateTaskTitle(taskService)
            5 -> deleteTask(taskService)
            6 -> filterTasksByStatus(taskService)
            7 -> filterTasksByPriority(taskService)
            0 -> {
                println("¡Hasta pronto!")
                running = false
            }
            else -> println("Opción no válida. Intente de nuevo.")
        }
    }
}

fun printMenu() {
    println("\n--- MENÚ PRINCIPAL ---")
    println("1. Crear nueva tarea")
    println("2. Listar todas las tareas")
    println("3. Marcar tarea como completada")
    println("4. Actualizar título de tarea")
    println("5. Eliminar tarea")
    println("6. Filtrar tareas por estado")
    println("7. Filtrar tareas por prioridad")
    println("0. Salir")
    print("Seleccione una opción: ")
}

fun createNewTask(taskService: TaskService) {
    println("\n--- CREAR NUEVA TAREA ---")
    print("Título: ")
    val title = readLine() ?: ""
    
    print("Descripción (opcional): ")
    val description = readLine() ?: ""
    
    print("Fecha límite (yyyy-MM-dd) (opcional): ")
    val dueDateStr = readLine()
    val dueDate = if (!dueDateStr.isNullOrBlank()) {
        try {
            LocalDate.parse(dueDateStr, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: DateTimeParseException) {
            println("Formato de fecha inválido. No se asignará fecha límite.")
            null
        }
    } else null
    
    print("Prioridad (1:BAJA, 2:MEDIA, 3:ALTA) [2]: ")
    val priority = when (readLine()?.toIntOrNull() ?: 2) {
        1 -> Priority.LOW
        3 -> Priority.HIGH
        else -> Priority.MEDIUM
    }
    
    val task = taskService.createTask(title, description, dueDate, priority)
    println("¡Tarea creada con éxito! ID: ${task.id}")
}

fun listAllTasks(taskService: TaskService) {
    println("\n--- LISTA DE TAREAS ---")
    val tasks = taskService.getAllTasks()
    if (tasks.isEmpty()) {
        println("No hay tareas registradas.")
        return
    }
    
    tasks.forEach { task ->
        val status = if (task.isCompleted) "✓" else "○"
        val dueDate = task.dueDate?.format(DateTimeFormatter.ISO_LOCAL_DATE) ?: "Sin fecha"
        println("$status [${task.id}] ${task.title} - Prioridad: ${task.priority} - Fecha: $dueDate")
        if (task.description.isNotBlank()) {
            println("   Descripción: ${task.description}")
        }
    }
}

fun markTaskAsCompleted(taskService: TaskService) {
    println("\n--- MARCAR TAREA COMO COMPLETADA ---")
    print("ID de la tarea: ")
    val id = readLine()?.toIntOrNull()
    if (id == null) {
        println("ID inválido.")
        return
    }
    
    if (taskService.markAsCompleted(id)) {
        println("¡Tarea marcada como completada!")
    } else {
        println("No se encontró la tarea con ID $id.")
    }
}

fun updateTaskTitle(taskService: TaskService) {
    println("\n--- ACTUALIZAR TÍTULO DE TAREA ---")
    print("ID de la tarea: ")
    val id = readLine()?.toIntOrNull()
    if (id == null) {
        println("ID inválido.")
        return
    }
    
    print("Nuevo título: ")
    val newTitle = readLine() ?: ""
    
    if (taskService.updateTaskTitle(id, newTitle)) {
        println("¡Título actualizado con éxito!")
    } else {
        println("No se encontró la tarea con ID $id.")
    }
}

fun deleteTask(taskService: TaskService) {
    println("\n--- ELIMINAR TAREA ---")
    print("ID de la tarea: ")
    val id = readLine()?.toIntOrNull()
    if (id == null) {
        println("ID inválido.")
        return
    }
    
    if (taskService.deleteTask(id)) {
        println("¡Tarea eliminada con éxito!")
    } else {
        println("No se encontró la tarea con ID $id.")
    }
}

fun filterTasksByStatus(taskService: TaskService) {
    println("\n--- FILTRAR POR ESTADO ---")
    println("1. Pendientes")
    println("2. Completadas")
    print("Seleccione una opción: ")
    
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("\n--- TAREAS PENDIENTES ---")
            taskService.getPendingTasks().forEach { task ->
                println("○ [${task.id}] ${task.title} - Prioridad: ${task.priority}")
            }
        }
        2 -> {
            println("\n--- TAREAS COMPLETADAS ---")
            taskService.getCompletedTasks().forEach { task ->
                println("✓ [${task.id}] ${task.title} - Prioridad: ${task.priority}")
            }
        }
        else -> println("Opción no válida.")
    }
}

fun filterTasksByPriority(taskService: TaskService) {
    println("\n--- FILTRAR POR PRIORIDAD ---")
    println("1. Baja")
    println("2. Media")
    println("3. Alta")
    print("Seleccione una opción: ")
    
    val priority = when (readLine()?.toIntOrNull()) {
        1 -> Priority.LOW
        2 -> Priority.MEDIUM
        3 -> Priority.HIGH
        else -> {
            println("Opción no válida.")
            return
        }
    }
    
    println("\n--- TAREAS DE PRIORIDAD ${priority} ---")
    val tasks = taskService.getTasksByPriority(priority)
    if (tasks.isEmpty()) {
        println("No hay tareas con esta prioridad.")
        return
    }
    
    tasks.forEach { task ->
        val status = if (task.isCompleted) "✓" else "○"
        println("$status [${task.id}] ${task.title}")
    }
}