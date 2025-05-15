package com.marcosoft.danceuniversity.firstAppActivty.toDoList

import android.app.Dialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.marcosoft.danceuniversity.R

class TodoActivity : AppCompatActivity() {

    private val categories = listOf<TaskCategory>(
        TaskCategory.Personal, TaskCategory.Business,
        TaskCategory.Other
    )

    private val tasks = mutableListOf(
        Task("PruebaBusiness", TaskCategory.Business),
        Task("PRuebaPersonal", TaskCategory.Personal),
        Task("PruebaOther", TaskCategory.Other)
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var abAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        abAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: AppCompatButton = dialog.findViewById<AppCompatButton>(R.id.btnAddTask)
        var etTask: AppCompatEditText = dialog.findViewById<AppCompatEditText>(R.id.etTask)
        var rgCategories = dialog.findViewById<RadioGroup>(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val task: String = etTask.text.toString()
            if (task.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton =
                    rgCategories.findViewById<AppCompatRadioButton>(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.business) -> TaskCategory.Business
                    getString(R.string.personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }
                tasks.add(0, Task(task, currentCategory))
                updateTasks()
                dialog.hide()
            }

        }

        dialog.show()

    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
    }

    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        abAddTask = findViewById<FloatingActionButton>(R.id.abAddTask)
    }

    private fun updateTasks() {
        tasksAdapter.notifyDataSetChanged()
    }

}