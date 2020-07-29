package com.example.callforcode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.auth_main.*

class AuthActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_main)

        setup()
    }

    private fun setup() {
        button_register.setOnClickListener {
            if(email_login.text.isNotEmpty() && password_login.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_login.text.toString(), password_login.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        successRegister()
                    }else{
                        alertRegisterError()
                    }
                }
            }else{
                alertEmptyFields()
            }
        }

        button_enter.setOnClickListener {
            if(email_login.text.isNotEmpty() && password_login.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email_login.text.toString(), password_login.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        successRegister()
                    }else{
                        alertLogInError()
                    }
                }
            }else{
                alertEmptyFields()
            }
        }
    }

    private fun alertRegisterError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error en el registro")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun alertLogInError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error en el usuario o contraseña")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun alertEmptyFields() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Uno de los campos o ambos se encuentran vacios")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private  fun successRegister(){
        val mainIntent = Intent(this,MainActivity::class.java)
        startActivity(mainIntent)
    }
}