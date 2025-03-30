package model

import java.time.LocalDate

data class Task(
    val id: Int,
    var title: String,
    var description: String = "",
    var dueDate: LocalDate? = null,
    var isCompleted: Boolean = false,
    var priority: Priority = Priority.MEDIUM
)

enum class Priority {
    LOW, MEDIUM, HIGH
}