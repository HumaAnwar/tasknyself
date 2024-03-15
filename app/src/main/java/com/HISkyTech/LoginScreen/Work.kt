package com.HISkyTech.LoginScreen

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.HISkyTech.LoginScreen.Adapters.AdapterTask
import com.HISkyTech.LoginScreen.Models.task_model
import com.HISkyTech.LoginScreen.databinding.ActivityWorkBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Work : AppCompatActivity(),AdapterTask.OnItemClickListener {

    private var db=FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivityWorkBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWorkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
sharedPreferences=getSharedPreferences("preference", Context.MODE_PRIVATE)
        var editor=sharedPreferences.edit()
        var from = intent.getStringExtra("From")


        if(from.equals("Work"))
        {

            db.collection("TaskCollection")
                .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString()).whereEqualTo("catagory","Work")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        var taskList = ArrayList<task_model>()
                        for (task in task.result) {
                            val modelTask = task.toObject(task_model::class.java)
                            taskList.add(modelTask)
                            taskList.sortBy { it.title }
                        }

                        Toast.makeText(this@Work, taskList.size.toString(), Toast.LENGTH_SHORT).show()

                        binding.rvwork.layoutManager = GridLayoutManager(this,2)
                        binding.rvwork.adapter = AdapterTask(this, taskList,this@Work )
                    }
                }
        }
        if(from.equals("Food"))
        {

            db.collection("TaskCollection")
                .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString()).whereEqualTo("catagory","Food")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        var taskList = ArrayList<task_model>()
                        for (task in task.result) {
                            val modelTask = task.toObject(task_model::class.java)
                            taskList.add(modelTask)
                            taskList.sortBy { it.title }
                        }

                        Toast.makeText(this@Work, taskList.size.toString(), Toast.LENGTH_SHORT).show()

                        binding.rvwork.layoutManager = GridLayoutManager(this,2)
                        binding.rvwork.adapter = AdapterTask(this, taskList,this@Work )
                    }
                }
        }
        if(from.equals("Music"))
        {

            db.collection("TaskCollection")
                .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString()).whereEqualTo("catagory","Music")
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
                        binding.rvwork.adapter = AdapterTask(this, taskList,this@Work )
                    }
                }
        }


        binding.workadd.setOnClickListener()
        {
            if (from.equals("Work")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                var title=dialog.findViewById<EditText>(R.id.travel_name)
                var description=dialog.findViewById<EditText>(R.id.travel_des)
                var btnAdd=dialog.findViewById<Button>(R.id.travel_btn)

                btnAdd.setOnClickListener()
                {
                    var taskmodel= task_model(title.text.toString(),description.text.toString(),"15-3-24","Work","")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener {document->

                            taskmodel.task_id=document.id.toString()
                            taskmodel.userId=sharedPreferences.getString("userId","")!!
                            Toast.makeText(this@Work, "Succesfull add work task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
dialog.dismiss()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }
                dialog.setCancelable(false)
dialog.show()


            }
  if (from.equals("Food")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
      dialog.window?.setLayout(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT
      )

                var title=dialog.findViewById<EditText>(R.id.travel_name)
                var description=dialog.findViewById<EditText>(R.id.travel_des)


                var btnAdd=dialog.findViewById<Button>(R.id.travel_btn)

                btnAdd.setOnClickListener()
                {
                    var taskmodel= task_model(title.text.toString(),description.text.toString(),"15-3-24","Food","")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener {document->

                            taskmodel.task_id=document.id.toString()
                            taskmodel.userId=sharedPreferences.getString("userId","")!!
                            Toast.makeText(this@Work, "Succesfull add food task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
dialog.dismiss()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }
                dialog.setCancelable(false)
dialog.show()


            }

            if (from.equals("Music")) {
                var dialog = Dialog(this@Work)

                dialog.setContentView(R.layout.travel_task)
                var title=dialog.findViewById<EditText>(R.id.travel_name)
                var description=dialog.findViewById<EditText>(R.id.travel_des)
               var dat=dialog.findViewById<EditText>(R.id.travel_date)
                var btnAdd=dialog.findViewById<Button>(R.id.travel_btn)

                dat.visibility= View.GONE
dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel= task_model(title.text.toString(),description.text.toString(),"15-3-24","Music","")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener {document->
dialog.dismiss()
                            taskmodel.task_id=document.id.toString()
                            taskmodel.userId=sharedPreferences.getString("userId","")!!
                            Toast.makeText(this@Work, "Succesfull add work task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)

                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

dialog.show()
            }
        }








    }

    override fun onItemClick(taskModel: task_model) {

    }

    override fun onDeleteClick(taskModel: task_model) {

    }

    override fun onEditClick(taskModel: task_model) {
if(taskModel.Catagory.equals("Work"))
{


}
    }
}