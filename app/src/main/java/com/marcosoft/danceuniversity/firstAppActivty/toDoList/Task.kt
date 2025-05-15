package com.marcosoft.danceuniversity.firstAppActivty.toDoList

data class Task(val name:String, val category: TaskCategory, var isSelected:Boolean = false) {
}