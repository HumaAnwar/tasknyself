package com.HISkyTech.LoginScreen.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.HISkyTech.LoginScreen.Models.Task_model
import com.HISkyTech.LoginScreen.R
import java.text.SimpleDateFormat
import java.util.Locale

class AdapterTask ( private val context: Context, private val dataList: List<Task_model>,private val listner:OnItemClickListener) :
  RecyclerView.Adapter<AdapterTask.ViewHolder>() {
    interface OnItemClickListener {

        fun onItemClick(taskModel: Task_model)
        fun onDeleteClick(taskModel: Task_model)
        fun onEditClick(taskModel: Task_model)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val task_title: TextView = itemView.findViewById(R.id.task_name)
        private val container: CardView = itemView.findViewById(R.id.containeruser)
        var edit=itemView.findViewById<ImageButton>(R.id.editbtn)
        var del=itemView.findViewById<ImageButton>(R.id.delete)

        fun bind(taskModel: Task_model) {

            task_title.text = taskModel.title

            del.setOnClickListener()
            {
                listner.onDeleteClick(taskModel)
            }
            edit.setOnClickListener()
            {
                listner.onEditClick(taskModel)
            }


            val dateTimeFormat = SimpleDateFormat("dd MMMM yyyy, h:mm a", Locale.getDefault())
            val formattedDateTime = dateTimeFormat.format(taskModel.createdAt.toDate())
            itemView.findViewById<TextView>(R.id.uploadedAt).text = formattedDateTime


        }

    }

    companion object {
        fun notifyDataSetChanged() {

        }

    }


}
