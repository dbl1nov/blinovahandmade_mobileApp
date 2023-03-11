package com.example.blinovahandmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blinovahandmade.databinding.ActivityCartBinding
import com.example.blinovahandmade.databinding.ActivityLoginBinding



class CartActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityCartBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }
}