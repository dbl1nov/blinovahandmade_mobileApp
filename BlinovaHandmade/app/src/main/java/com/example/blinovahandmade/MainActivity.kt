package com.example.blinovahandmade

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.blinovahandmade.databinding.ActivityMainBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding
//    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
//
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
//        setupWithNavController(bindingClass.bottomNav, navController)


        val db = Firebase.firestore

        val docRef = db.collection("categories").document("Pu5Z0ZzvN8C2DjUJSwDf")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    bindingClass.tvCategory.text = document.data?.get("name").toString()
                    val imgName = document.data?.get("photoKotlin").toString()
                    val storageRef = FirebaseStorage.getInstance().reference.child(imgName)
                    val localFile = File.createTempFile("tempImg", "png")
                    storageRef.getFile(localFile).addOnSuccessListener {
                        val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                        bindingClass.imgCategory.setImageBitmap(bitmap)
                        bindingClass.progressBarId.visibility = View.GONE
                    }
                        .addOnFailureListener {
                            Toast.makeText(this, "ОшИбОЧКА", Toast.LENGTH_SHORT).show()
                        }


                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }


        bindingClass.imgBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        }
    }













