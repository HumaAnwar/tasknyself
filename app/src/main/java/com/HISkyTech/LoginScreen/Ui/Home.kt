package com.HISkyTech.LoginScreen.Ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.text.toUpperCase
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.HISkyTech.LoginScreen.Adapters.AdapterTask
import com.HISkyTech.LoginScreen.Models.task_model
import com.HISkyTech.LoginScreen.R
import com.HISkyTech.LoginScreen.databinding.ActivityHomeBinding
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.Locale


class Home : AppCompatActivity() ,AdapterTask.OnItemClickListener {
    private lateinit var binding: ActivityHomeBinding
     private lateinit var dialog: Dialog
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar
    private lateinit var sharedPreferences: SharedPreferences
     private lateinit var itemList: List<task_model>
     private lateinit var filteredList: MutableList<task_model>
     private lateinit var dialogDetail: Dialog
    private var db = Firebase.firestore

    var list= ArrayList<task_model> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)







        binding.addTask.setOnClickListener {
            showChoiceDialog()
        }
        setAdaptertask()

        binding.tvSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(newText)
                }
                return false
            }
        })

    }
    private fun filter(text: String) {
        val filteredList = ArrayList<task_model>()

        for (task in list) {
            val titleInUpperCase = task.title.toUpperCase(Locale.getDefault())
            val searchTextInLowerCase = text.lowercase(Locale.getDefault())

            if (titleInUpperCase.contains(searchTextInLowerCase)) {
                filteredList.add(task)
            }
        }

        if (filteredList.isEmpty()) {
            // If no items are added to the filtered list, display a toast message indicating no data found.

        } else {
            // If there are filtered items, set up a new adapter with the filtered list and update the RecyclerView.
            binding.rv.adapter = AdapterTask(this, filteredList, this@Home)
        }
    }



    private fun showChoiceDialog() {
        val builder = AlertDialog.Builder(this)

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



                binding.rv.layoutManager = GridLayoutManager(this,2)
                binding.rv.adapter = AdapterTask(this, taskList,this )
            }
        }
    }

     @SuppressLint("CutPasteId")
     override fun onItemClick(taskModel: task_model) {
}



     private fun deletetask(taskModel: task_model) {

         db.collection("Tasks").document(taskModel.task_id).delete()
             .addOnSuccessListener{
                 Toast.makeText(this, "delete successfully", Toast.LENGTH_SHORT).show()
                 setAdaptertask()
             }
             .addOnFailureListener {
                 Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
             }

     }
     private fun update_task(taskModel: task_model) {
         db.collection("Tasks").document(taskModel.task_id).set(taskModel)
             .addOnSuccessListener{
                 Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
                 setAdaptertask()
             }
             .addOnFailureListener {
                 Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()
             }
     }
     override fun onEditClick(taskModel: task_model) {

        }
     override fun onDeleteClick(taskModel: task_model) {
     }



    }

