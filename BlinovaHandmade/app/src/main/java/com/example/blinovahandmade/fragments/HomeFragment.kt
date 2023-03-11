package com.example.blinovahandmade.fragments

import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blinovahandmade.LoginActivity
import com.example.blinovahandmade.R
import com.example.blinovahandmade.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class HomeFragment : Fragment() {
    private lateinit var bindingClass: FragmentHomeBinding
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {
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
                            Toast.makeText(requireActivity(), "your message", Toast.LENGTH_LONG).show()
                        }


                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }
        bindingClass.imgBtn.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            requireActivity().startActivity(intent)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)



    }


}