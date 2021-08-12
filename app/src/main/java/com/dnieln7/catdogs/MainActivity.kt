package com.dnieln7.catdogs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dnieln7.catdogs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }
}