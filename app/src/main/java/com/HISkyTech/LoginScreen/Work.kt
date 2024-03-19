package com.HISkyTech.LoginScreen

import android.app.Dialog
import android.content.Context
import androidx.core.content.ContextCompat

import android.content.Intent
import android.content.SharedPreferences

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.collection.emptyLongSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.HISkyTech.LoginScreen.Adapters.AdapterTask
import com.HISkyTech.LoginScreen.Models.task_model
import com.HISkyTech.LoginScreen.Ui.different_tasks
import com.HISkyTech.LoginScreen.databinding.ActivityWorkBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Work : AppCompatActivity(),AdapterTask.OnItemClickListener {

    private var db = FirebaseFirestore.getInstance()
    private lateinit var sharedPreferences: SharedPreferences



    private lateinit var binding:ActivityWorkBinding
    private lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityWorkBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)
        var editor = sharedPreferences.edit()
        var from = intent.getStringExtra("From")

        if (from.equals("Work")) {
            binding.wt.text = "WORK TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_work_outline_24)
            workshow()
        }
        else if (from.equals("Food")) {
            binding.workcv.setCardBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            binding.wt.text = "FOODD TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_food_bank_24)
            showfood()
        }
        else if (from.equals("Music")) {
            binding.wt.text = "MUSIC TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_music_note_24)
            showmusic()
        }
        else if (from.equals("Bills")) {
            binding.wt.text = "BILL TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_send_to_mobile_24)
            showbill()
        }
        else if (from.equals("Shopping")) {
            binding.wt.text = "SHOPPING TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_shopping_cart_24)
            showshopping()
        }
        else if (from.equals("Travel")) {
            binding.wt.text = "TRAVEL TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_flight_24)
            showtravel()
        }
        else if (from.equals("Study")) {
            binding.wt.text = "STUDY TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_library_books_24)
            showstudy()
        }
        else if (from.equals("Home")) {
            binding.wt.text = "HOME TASK"
            binding.iconwrk.setImageResource(R.drawable.baseline_add_home_work_24)
            showhome()
        }

        binding.workadd.setOnClickListener()
        {
            if (from.equals("Work")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                Am.visibility=View.GONE
               quant.visibility=View.GONE

                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(title.text.toString(), description.text.toString(), "15-3-24", "Work", "")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->

                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(this@Work, "Succesfull add work task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)

                            dialog.dismiss()
                            workshow()
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

                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }

                Am.visibility = View.GONE
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(title.text.toString(), quant.text.toString(), description.text.toString(), "15-3-24", "Food", "")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->

                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(
                                this@Work,
                                "Succesfull add food task",
                                Toast.LENGTH_SHORT
                            ).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
                            dialog.dismiss()
                            showfood()
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
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Am=dialog.findViewById<EditText>(R.id.amount)

                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                dat.visibility = View.GONE
                 quant.visibility = View.GONE
                 Am.visibility = View.GONE

                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(title.text.toString(), description.text.toString(), "15-3-24", "Music", "")
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(this@Work, "Succesfull add work task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
                            showmusic()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

                dialog.show()
            }
            if (from.equals("Bills")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                dat.visibility = View.GONE
                 quant.visibility = View.GONE
                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(
                        title.text.toString(),
                        description.text.toString(),
                        Am.text.toString(),
                        "15-3-24",
                        "Bills",
                        ""
                    )
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(
                                this@Work,
                                "Succesfull add Bills task",
                                Toast.LENGTH_SHORT
                            ).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
                            showbill()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

                dialog.show()
            }
            if (from.equals("Shopping")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }



                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(
                        title.text.toString(),
                        description.text.toString(),
                        Am.text.toString(),
                        quant.text.toString(),
                        "15-3-24",
                        "Shopping",
                        ""
                    )
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(this@Work, "Succesfull add shopping task", Toast.LENGTH_SHORT).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
                            showshopping()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

                dialog.show()
            }
            if (from.equals("Travel")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                dat.visibility = View.GONE

                quant.visibility = View.GONE
                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(
                        title.text.toString(),
                        description.text.toString(),
                        Am.text.toString(),

                        "15-3-24",
                        "Travel",
                        ""
                    )
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(
                                this@Work,
                                "Succesfull add travel task",
                                Toast.LENGTH_SHORT
                            ).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
 showtravel()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

                dialog.show()
            }
            if (from.equals("Study")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                dat.visibility = View.GONE
                 Am.visibility = View.GONE
                 quant.visibility = View.GONE

                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(
                        title.text.toString(),
                        description.text.toString(),
                        "15-3-24",
                        "Study",
                        ""
                    )
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(
                                this@Work,
                                "Succesfull add study task",
                                Toast.LENGTH_SHORT
                            ).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
showstudy()
                        }

                        .addOnFailureListener {
                            dialog.dismiss()
                            Toast.makeText(this@Work, "Failed", Toast.LENGTH_SHORT).show()
                        }

                }

                dialog.show()
            }
            if (from.equals("Home")) {
                var dialog = Dialog(this@Work)
                dialog.setContentView(R.layout.travel_task)
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title = dialog.findViewById<EditText>(R.id.travel_name)
                var description = dialog.findViewById<EditText>(R.id.travel_des)
                var dat = dialog.findViewById<EditText>(R.id.travel_date)
                var btnAdd = dialog.findViewById<Button>(R.id.travel_btn)
                var Am=dialog.findViewById<EditText>(R.id.amount)
                var quant=dialog.findViewById<EditText>(R.id.quantity)
                var Back = dialog.findViewById<ImageView>(R.id.back)
                Back.setOnClickListener() {
                    dialog.dismiss()
                }
                dat.visibility = View.GONE
                Am.visibility = View.GONE
                quant.visibility = View.GONE

                dialog.setCancelable(false)
                btnAdd.setOnClickListener()
                {
                    var taskmodel = task_model(
                        title.text.toString(),
                        description.text.toString(),
                        "15-3-24",
                        "Home",
                        ""
                    )
                    db.collection("TaskCollection").add(taskmodel)

                        .addOnSuccessListener { document ->
                            dialog.dismiss()
                            taskmodel.task_id = document.id.toString()
                            taskmodel.userId = sharedPreferences.getString("userId", "")!!
                            Toast.makeText(
                                this@Work,
                                "Succesfull add home task",
                                Toast.LENGTH_SHORT
                            ).show()

                            db.collection("TaskCollection").document(document.id).set(taskmodel)
showhome()
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

    private fun showmusic() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Music")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showbill() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Bills")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showshopping() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Shopping")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }


                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showtravel() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Travel")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showhome() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Home")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showfood() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Food")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }

                    Toast.makeText(this@Work, taskList.size.toString(), Toast.LENGTH_SHORT)
                        .show()

                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun showstudy() {
        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Study")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }



                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    private fun workshow() {

        db.collection("TaskCollection")
            .whereEqualTo("userId", sharedPreferences.getString("userId", "").toString())
            .whereEqualTo("catagory", "Work")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    var taskList = ArrayList<task_model>()
                    for (task in task.result) {
                        val modelTask = task.toObject(task_model::class.java)
                        taskList.add(modelTask)
                        taskList.sortBy { it.title }
                    }

                    Toast.makeText(this@Work, taskList.size.toString(), Toast.LENGTH_SHORT)
                        .show()

                    binding.rvwork.layoutManager = LinearLayoutManager(this)
                    binding.rvwork.adapter = AdapterTask(this, taskList, this@Work)
                }
            }
    }

    override fun onItemClick(taskModel: task_model) {

    }

    override fun onDeleteClick(taskModel: task_model) {
        var from = intent.getStringExtra("From")
        db.collection("TaskCollection").document(taskModel.task_id).delete()
            .addOnSuccessListener() {
                Toast.makeText(this@Work, "Deleted", Toast.LENGTH_SHORT).show()

                if(from.equals("Work")){

                  workshow()
                }
                else if(from.equals("Study")){
                        showstudy()
                    }
                else if(from.equals("Food")){
                    showfood()
                    }
                else if(from.equals("Home")){
                   showhome()
                    }
                else if(from.equals("Travel")){
                  showtravel()
                    }
                else if(from.equals("Shopping")){
                 showshopping()
                    }
                else if(from.equals("Bill")){
                 showbill()
                    }
                else
                {
                   showmusic()
                }



            }
            .addOnFailureListener() {
                Toast.makeText(this@Work, "not Deleted", Toast.LENGTH_SHORT).show()

            }
    }

    override fun onEditClick(taskModel: task_model) {
        val dialog = Dialog(this@Work)
        var from = intent.getStringExtra("From")
        dialog.setContentView(R.layout.dialog_detail_task)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.setCancelable(false)
        var title = dialog.findViewById<EditText>(R.id.editTextTitle)
        var description = dialog.findViewById<EditText>(R.id.editTextDescription)
        var quantity = dialog.findViewById<EditText>(R.id.editTextquantity)
        var amount = dialog.findViewById<EditText>(R.id.editTextamount)
        var date = dialog.findViewById<EditText>(R.id.editTextdate)
        var update = dialog.findViewById<Button>(R.id.update_task)
        var cancel = dialog.findViewById<Button>(R.id.cancel_task)
        var delete = dialog.findViewById<Button>(R.id.delete)

        title.setText(taskModel.title.toString())
        description.setText(taskModel.description.toString())

        cancel.setOnClickListener()
        {
            dialog.dismiss()
        }

        update.setOnClickListener(
        )
        {
            taskModel.title = title.text.toString()
            taskModel.description = description.text.toString()

            db.collection("TaskCollection").document(taskModel.task_id).set(taskModel)
                .addOnSuccessListener() {
                    Toast.makeText(this, "update successfull", Toast.LENGTH_SHORT).show()

                    if(from.equals("Work")){

                        workshow()
                    }
                    else if(from.equals("Study")){
                        showstudy()
                    }
                    else if(from.equals("Food")){
                        showfood()
                    }
                    else if(from.equals("Home")){
                        showhome()
                    }
                    else if(from.equals("Travel")){
                        showtravel()
                    }
                    else if(from.equals("Shopping")){
                        showshopping()
                    }
                    else if(from.equals("Bill")){
                        showbill()
                    }
                    else
                    {
                        showmusic()
                    }
                    dialog.dismiss()

                }
                .addOnFailureListener {
                    Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show()
                }
        }

        dialog.show()
    }

    }

