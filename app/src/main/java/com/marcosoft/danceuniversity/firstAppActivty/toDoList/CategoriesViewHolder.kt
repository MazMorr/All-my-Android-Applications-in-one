package com.marcosoft.danceuniversity.firstAppActivty.toDoList

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.marcosoft.danceuniversity.R
import kotlin.coroutines.coroutineContext

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)

    fun render(taskCategory: TaskCategory) {
        when(taskCategory){
            TaskCategory.Business ->{
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.blue))
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.green))
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(ContextCompat.getColor(divider.context, R.color.red))
            }
        }
    }
}