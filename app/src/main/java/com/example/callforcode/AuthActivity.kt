package com.example.callforcode

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.auth_main.*

class AuthActivity : AppCompatActivity(){

    private val GOOGLE_SIGN_IN = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_main)
        setup()
        session()
        Thread.sleep(500)
    }

    override fun onStart() {
        super.onStart()
        authLayout.visibility =  View.VISIBLE
    }

    private fun session() {
        val prefs: SharedPreferences? = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val  email = prefs?.getString("email",null)
        val provider = prefs?.getString("provider",null)


        if(email != null && provider != null){
            authLayout.visibility = View.INVISIBLE
            successRegister(email, ProviderType.valueOf(provider))
        }
    }

    private fun setup() {
        button_register.setOnClickListener {
            if(email_login.text.isNotEmpty() && password_login.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email_login.text.toString(), password_login.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        successRegister(it.result?.user?.email ?: "", ProviderType.BASIC)
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
                        successRegister(it.result?.user?.email ?: "", ProviderType.BASIC)
                    }else{
                        alertLogInError()
                    }
                }
            }else{
                alertEmptyFields()
            }
        }

        google_button.setOnClickListener {

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build()

            val googleClient = GoogleSignIn.getClient(this,googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
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

    private  fun successRegister(email:String, provider:ProviderType){
        val mainIntent = Intent(this,MainActivity::class.java).apply {

            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(mainIntent)
    }

    private  fun successGoogleRegister(email:String, name:String, provider:ProviderType){
        val mainIntent = Intent(this,MainActivity::class.java).apply {

            putExtra("email",email)
            putExtra("provider",provider.name)
            putExtra("name",name)
        }
        startActivity(mainIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                        if(it.isSuccessful){
                            successGoogleRegister(account.email ?: "",account.displayName.toString() , ProviderType.GOOGLE)
                        }else{
                            alertLogInError()
                        }
                    }
            }
        }catch (e:ApiException){
                alertLogInError()
            }
        }
    }
}