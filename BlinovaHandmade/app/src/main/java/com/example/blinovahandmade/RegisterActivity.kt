package com.example.blinovahandmade

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.blinovahandmade.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {



    lateinit var auth: FirebaseAuth

    private lateinit var bindingClass: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        auth = FirebaseAuth.getInstance()

        val bSignUp = bindingClass.bSignUp

        bSignUp.setOnClickListener{
            registerUser()
        }

    }

    private fun registerUser(){
        val etMail = bindingClass.etMail
        val etPassword = bindingClass.etPassword




        val email = etMail.text.toString()
        val password = etPassword.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    auth.createUserWithEmailAndPassword(email, password)
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                    }
                }catch(e: java.lang.Exception) {
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegisterActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
private fun checkLoggedInState(){
    val tvLogged = bindingClass.tvLogged

    if(auth.currentUser == null){
        tvLogged.visibility = View.VISIBLE
        tvLogged.text = "Ошибка! введенное имя пользователя или пароль!"
    }else{
        tvLogged.visibility = View.VISIBLE
        tvLogged.text = "Вы успешно зарегестрировались!"
    }
}

}




