package repository

import model.Task

class TaskRepository {
    private val tasks = mutableListOf<Task>()
    private var nextId = 1

    fun addTask(task: Task): Task {
        val newTask = task.copy(id = nextId++)
        tasks.add(newTask)
        return newTask
    }

    fun getTasks(): List<Task> = tasks.toList()

    fun getTaskById(id: Int): Task? = tasks.find { it.id == id }

    fun updateTask(task: Task): Boolean {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task
            return true
        }
        return false
    }

    fun deleteTask(id: Int): Boolean {
        return tasks.removeIf { it.id == id }
    }
}