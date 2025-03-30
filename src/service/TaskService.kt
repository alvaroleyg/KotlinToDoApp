package service

import model.Priority
import model.Task
import repository.TaskRepository
import java.time.LocalDate

class TaskService(private val repository: TaskRepository) {
    
    fun createTask(title: String, description: String = "", dueDate: LocalDate? = null, priority: Priority = Priority.MEDIUM): Task {
        val task = Task(0, title, description, dueDate, false, priority)
        return repository.addTask(task)
    }
    
    fun getAllTasks(): List<Task> = repository.getTasks()
    
    fun getPendingTasks(): List<Task> = repository.getTasks().filter { !it.isCompleted }
    
    fun getCompletedTasks(): List<Task> = repository.getTasks().filter { it.isCompleted }
    
    fun getTasksByPriority(priority: Priority): List<Task> = repository.getTasks().filter { it.priority == priority }
    
    fun markAsCompleted(id: Int): Boolean {
        val task = repository.getTaskById(id) ?: return false
        task.isCompleted = true
        return repository.updateTask(task)
    }
    
    fun updateTaskTitle(id: Int, title: String): Boolean {
        val task = repository.getTaskById(id) ?: return false
        task.title = title
        return repository.updateTask(task)
    }
    
    fun deleteTask(id: Int): Boolean = repository.deleteTask(id)
}