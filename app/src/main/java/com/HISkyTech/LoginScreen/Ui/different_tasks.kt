package com.HISkyTech.LoginScreen.Ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import com.HISkyTech.LoginScreen.Bills
import com.HISkyTech.LoginScreen.FoodTask
import com.HISkyTech.LoginScreen.Home
import com.HISkyTech.LoginScreen.Music
import com.HISkyTech.LoginScreen.R
import com.HISkyTech.LoginScreen.Shopping
import com.HISkyTech.LoginScreen.Study
import com.HISkyTech.LoginScreen.Travel
import com.HISkyTech.LoginScreen.Work
import com.HISkyTech.LoginScreen.databinding.ActivityDifferentTasksBinding
import com.google.android.material.navigation.NavigationView

class different_tasks : AppCompatActivity() {
    private lateinit var binding: ActivityDifferentTasksBinding
  lateinit var toggle: ActionBarDrawerToggle

  private var isDarkTheme: Boolean = false
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityDifferentTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)



      val sharedPreferences = getSharedPreferences("preference", Context.MODE_PRIVATE)
      val editor = sharedPreferences.edit()
      val toggle = ActionBarDrawerToggle(this, binding.maindrawer1,binding.toolbar, R.string.open, R.string.close)
      binding.maindrawer1.addDrawerListener(toggle)
      toggle.syncState()
      supportActionBar?.setDisplayHomeAsUpEnabled(true)

      binding.navigation.setNavigationItemSelectedListener { menuItem ->
        // Handle navigation item clicks here
        when (menuItem.itemId) {
          R.id.imagetodo->{
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()

          }
          R.id.home -> {
           startActivity(Intent(this,Home::class.java))

          }

          R.id.contactUsButton -> {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.contactus, null)
            val dialogBuilder = AlertDialog.Builder(this)
              .setView(dialogView)
              .setCancelable(true)
            val dialog = dialogBuilder.create()
            dialog.show()

            dialogView.findViewById<TextView>(R.id.phoneno).setOnClickListener {
              val phoneNumber = "+923227970173"
              val intent = Intent(Intent.ACTION_DIAL)
              intent.data = Uri.parse("tel:$phoneNumber")
              startActivity(intent)
              dialog.dismiss()
            }

            dialogView.findViewById<TextView>(R.id.mail).setOnClickListener {
              val email = "huma01122@gmail.com"
              val subject = "Regarding your app"
              val intent = Intent(Intent.ACTION_SENDTO)
              intent.data = Uri.parse("mailto:$email?subject=$subject")
              startActivity(intent)
              dialog.dismiss()
            }

            dialogView.findViewById<TextView>(R.id.whtsapno).setOnClickListener {
              val phoneNumber = "+923227970173"
              val subject = "Humiii is here"
              val url = "https://api.whatsapp.com/send?phone=$phoneNumber"
              val intent = Intent(Intent.ACTION_VIEW)
              intent.data = Uri.parse(url)
              startActivity(intent)
              dialog.dismiss()
            }

        }


          R.id.fav -> {
            Toast.makeText(this, "Favorite clicked", Toast.LENGTH_SHORT).show()
          }

          R.id.share -> {
            val appPackageName = "com.tencent.ig"
            try {
              startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: ActivityNotFoundException) {
              startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }

          }
          R.id.rat -> {
            val appPackageName = "com.tencent.ig"
            try {
              startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
            } catch (e: ActivityNotFoundException) {
              startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }

        }
          R.id.theme -> {
            isDarkTheme = !isDarkTheme

            if (isDarkTheme) {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }


          }
          R.id.noti -> {

          Toast.makeText(this, "Notification Clicked", Toast.LENGTH_SHORT).show()


        }
          R.id.logout -> {

          editor.putBoolean("IsLog", false)
          editor.apply()
          startActivity(Intent(this,Login::class.java))

          Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()

        }
        }
        binding.maindrawer1.closeDrawers()
        true


      }

      binding.work.setOnClickListener(){

        startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Work"))
        }
        binding.home.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Home"))
        }
        binding.Music.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Music"))
        }
        binding.food.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Food"))

        binding.bills.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Bills"))
        }
        binding.study.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Study"))
        }
        binding.travel.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Travel"))
        }

        binding.shopping.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Work::class.java).putExtra("From","Shopping"))
        }
    }}
  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if(toggle.onOptionsItemSelected(item)) {
      return true
    }
    return super.onOptionsItemSelected(item)
  }



}