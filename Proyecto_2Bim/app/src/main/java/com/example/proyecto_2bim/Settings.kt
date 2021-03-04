package com.example.proyecto_2bim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val bnv_navbar=findViewById<BottomNavigationView>(R.id.navbar_settings)
        bnv_navbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.go_search -> {
                    Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show()
                }
                R.id.go_back -> finish()
                R.id.go_comics -> {
                    Toast.makeText(this, "COMICS", Toast.LENGTH_SHORT).show()
                }
                R.id.go_favorites -> {
                    Toast.makeText(this, "FAVORITES", Toast.LENGTH_SHORT).show()
                }
                R.id.go_settings -> {//Nothing
                 }
            }
            true
        }
    }
}