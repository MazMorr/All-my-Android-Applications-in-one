package com.marcosoft.danceuniversity.firstAppActivty.toDoList

sealed class TaskCategory {
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other: TaskCategory()
}
