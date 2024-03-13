package com.HISkyTech.LoginScreen.Ui

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import com.HISkyTech.LoginScreen.Bills
import com.HISkyTech.LoginScreen.Food
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
  private lateinit var dialog: Dialog
  private var isDarkTheme: Boolean = false
  lateinit var drawerLayout: DrawerLayout
  lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
      binding= ActivityDifferentTasksBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


      drawerLayout = findViewById(R.id.drawerlayout)
      navigationView = findViewById(R.id.navigationView)


      val toggle = ActionBarDrawerToggle(
        this, drawerLayout, R.string.open, R.string.close
      )
      drawerLayout.addDrawerListener(toggle)
      toggle.syncState()



      drawerLayout.addDrawerListener(toggle)
      toggle.syncState()
      navigationView.setNavigationItemSelectedListener { menuItem ->
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
        drawerLayout.closeDrawers()
        true


      }

      binding.work.setOnClickListener(){
        startActivity(Intent(this@different_tasks,Work::class.java))
        }
        binding.home.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Home::class.java))
        }
        binding.Music.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Music::class.java))
        }
        binding.food.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Food::class.java))
        }
        binding.bills.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Bills::class.java))
        }
        binding.study.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Study::class.java))
        }
        binding.travel.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Travel::class.java))
        }

        binding.shopping.setOnClickListener(){
          startActivity(Intent(this@different_tasks,Shopping::class.java))
        }
    }
  private fun shareContent(content: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, content)
    val chooserIntent = Intent.createChooser(intent, "Share via")

    startActivity(chooserIntent)


  }
}