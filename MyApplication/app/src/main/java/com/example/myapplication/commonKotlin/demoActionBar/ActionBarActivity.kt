package com.example.myapplication.commonKotlin.demoActionBar

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class ActionBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_bar)
        setSupportActionBar(findViewById(R.id.toolbar))

        //Hidden action bar
        val actionBar = supportActionBar
//        actionBar?.hide()

        //Set icon action bar
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setLogo(R.mipmap.ic_launcher)
        actionBar?.setDisplayUseLogoEnabled(true)

        //set back button
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.menu1 -> {
                Log.d("ishrek", "menu1")
            }
            R.id.menu2 -> {
                Log.d("ishrek", "menu2")
            }
            R.id.menu3 -> {
                Log.d("ishrek", "menu3")
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // action khi an nut back o navigation va back cua phone
    override fun onBackPressed() {
        super.onBackPressed()
    }
}