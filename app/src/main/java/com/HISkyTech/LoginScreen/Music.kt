package com.HISkyTech.LoginScreen

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.HISkyTech.LoginScreen.Adapters.AdapterTask
import com.HISkyTech.LoginScreen.Models.task_model
import com.HISkyTech.LoginScreen.databinding.ActivityMusicBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Music : AppCompatActivity(),AdapterTask.OnItemClickListener  {
    private lateinit var binding: ActivityMusicBinding
    private lateinit var dialog: AlertDialog
    private var db = Firebase.firestore
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var itemList: List<task_model>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMusicBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.musicadd.setOnClickListener() {
            showChoiceDialog()

        }
    }
        private fun showChoiceDialog() {
            val builder = AlertDialog.Builder(this@Music)

            builder.setTitle("Select Option")
                .setPositiveButton("Add Task") { dialog, which ->
                    addTask()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    dialog.dismiss()
                }

            dialog = builder.create()
            dialog.show()
        }


    private fun addTask() { // Dismiss the choice dialog

        val dialog = Dialog(this, R.style.FullWidthDialog)
        dialog.setContentView(R.layout.add_task_dialog)
        dialog.setCancelable(false)
        dialog.show()

        var title = dialog.findViewById<EditText>(R.id.title)
        val description = dialog.findViewById<EditText>(R.id.descript)
        val date = dialog.findViewById<EditText>(R.id.date)
        val catagory = dialog.findViewById<EditText>(R.id.catagory)
        val priority = dialog.findViewById<EditText>(R.id.priority)
        val back = dialog.findViewById<ImageView>(R.id.back)
        val next = dialog.findViewById<Button>(R.id.btnNext) // Corrected reference
        dialog.setCancelable(false)

        back.setOnClickListener {
            dialog.dismiss()
        }
        // ...


// Check if 'next' is not null before setting the click listener
        next.setOnClickListener {
            if (title.text.toString().isEmpty() || description.text.toString().isEmpty() ||
                date.text.toString().isEmpty() || catagory.text.isEmpty() || priority.text.isEmpty()
            ) {
                Toast.makeText(this, "Please Enter All fields", Toast.LENGTH_SHORT).show()
            } else {
                val model = task_model()
                model.title = title.text.toString()
                model.description = description.text.toString()
                model.task_date = date.text.toString()
                model.Catagory = catagory.text.toString()
                model.priority = priority.text.toString()

                taskAdd(model)
                dialog.dismiss()


            }
        }
// ...

    }
    private fun taskAdd(model: task_model) {

        val sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)

        db.collection("Tasks").add(model).addOnSuccessListener { document ->
            model.task_id = document.id
            model.userId = sharedPreferences.getString("userId", "").toString()

            db.collection("Tasks").document(document.id).set(model)
                .addOnSuccessListener {
                    Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
                    setAdaptertask()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Task not added", Toast.LENGTH_SHORT).show()
                }
        }


    }
    private fun setAdaptertask() {
        db.collection("Tasks")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = GridLayoutManager(this,2)
                    binding.rvwork.adapter = AdapterTask(this, taskList,this)
                }
            }
    }

    override fun onItemClick(taskModel: task_model) {

    }

    override fun onDeleteClick(taskModel: task_model) {

    }

    override fun onEditClick(taskModel: task_model) {

    }
}
