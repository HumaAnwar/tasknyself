package com.HISkyTech.LoginScreen.Ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import com.HISkyTech.LoginScreen.Bills
import com.HISkyTech.LoginScreen.Home
import com.HISkyTech.LoginScreen.Music
import com.HISkyTech.LoginScreen.R
import com.HISkyTech.LoginScreen.Shopping
import com.HISkyTech.LoginScreen.Study
import com.HISkyTech.LoginScreen.Travel
import com.HISkyTech.LoginScreen.Work
import com.HISkyTech.LoginScreen.databinding.ActivityDifferentTasksBinding

class different_tasks : AppCompatActivity() {
    private lateinit var binding: ActivityDifferentTasksBinding
  private lateinit var dialog: Dialog
  private var isDarkTheme: Boolean = false
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityDifferentTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)




      val toggle = ActionBarDrawerToggle(
        this, binding.maindrawer1, R.string.open, R.string.close
      )
      binding.maindrawer1.addDrawerListener(toggle)
      toggle.syncState()



      binding.maindrawer1.addDrawerListener(toggle)
      toggle.syncState()
      binding.navigationView.setNavigationItemSelectedListener { menuItem ->
        // Handle navigation item clicks here
        when (menuItem.itemId) {
          R.id.imagetodo->{
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()

          }
          R.id.home -> {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()

          }

          R.id.contact -> {
            Toast.makeText(this, "Contact us clicked", Toast.LENGTH_SHORT)
              .show()
          }

          R.id.fav -> {
            Toast.makeText(this, "Favorite clicked", Toast.LENGTH_SHORT).show()
          }

          R.id.share -> {
            shareContent("This is the content you want to share.")

          }  R.id.rat -> {
          Toast.makeText(this, "Rate us Clicked", Toast.LENGTH_SHORT).show()

        }
          R.id.theme -> {
            isDarkTheme = !isDarkTheme

            if (isDarkTheme) {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
              AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }


          }   R.id.noti -> {
          Toast.makeText(this, "Notification Clicked", Toast.LENGTH_SHORT).show()

        } R.id.rem -> {
          Toast.makeText(this, "Reminder Clicked", Toast.LENGTH_SHORT).show()

        } R.id.logout -> {
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
  private fun shareContent(content: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, content)
    val chooserIntent = Intent.createChooser(intent, "Share via")

    startActivity(chooserIntent)
  }


}