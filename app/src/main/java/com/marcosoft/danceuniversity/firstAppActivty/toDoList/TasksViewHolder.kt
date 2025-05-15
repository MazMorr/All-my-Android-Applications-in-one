package com.marcosoft.danceuniversity.firstAppActivty.toDoList

import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.marcosoft.danceuniversity.R


class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTask: AppCompatTextView = view.findViewById(R.id.tvTask)
    private val cbTask: AppCompatCheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {
        tvTask.text = task.name
    }
}