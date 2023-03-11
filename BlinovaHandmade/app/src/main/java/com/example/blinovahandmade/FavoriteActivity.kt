package com.example.blinovahandmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blinovahandmade.databinding.ActivityCartBinding.inflate
import com.example.blinovahandmade.databinding.ActivityFavoriteBinding
import com.example.blinovahandmade.databinding.ActivityMainBinding

class FavoriteActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
}