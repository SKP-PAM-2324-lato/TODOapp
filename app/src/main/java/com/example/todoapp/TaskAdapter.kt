package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var tasks: List<String>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

   class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val taskTextView: TextView = itemView.findViewById(R.id.taskTextView)
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        holder.taskTextView.text = tasks[position]
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun update(newTasks: List<String>){
        tasks = newTasks
        notifyDataSetChanged()
    }


}