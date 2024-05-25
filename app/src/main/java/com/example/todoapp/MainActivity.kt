package com.example.todoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        databaseHelper = DatabaseHelper(this)

        val taskRecyclerView: RecyclerView = findViewById(R.id.taskRecyclerView)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(databaseHelper.getAllTask().toMutableList())
        taskRecyclerView.adapter = taskAdapter

        val addTaskButton: FloatingActionButton = findViewById(R.id.addTaskButton)
        val taskInput: TextInputEditText = findViewById(R.id.taskInput)

        addTaskButton.setOnClickListener{
            val task = taskInput.text.toString()
            if(task.isNotEmpty()){
                databaseHelper.addTask(task)
                taskAdapter.update(databaseHelper.getAllTask())
                taskInput.text?.clear()
            }
        }
    }
}