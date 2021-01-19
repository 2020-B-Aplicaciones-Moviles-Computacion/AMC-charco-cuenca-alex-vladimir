package com.example.firebase_assistant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val COD_SESION_INICIO=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngresar=findViewById<Button>(R.id.btn_ingresar);
        val btnSalir=findViewById<Button>(R.id.btn_salir);
        val btnClose=findViewById<Button>(R.id.btn_close);
        val tvLogIn=findViewById<TextView>(R.id.tv_login)


        btnIngresar.setOnClickListener {
            solicitarIngresarApp()
        }
        btnSalir.setOnClickListener {
            logOut()
        }
        btnClose.setOnClickListener {
            this.finish()
        }

        val instanceAuth=FirebaseAuth.getInstance()
        if(instanceAuth.currentUser!=null){
            //Ya esta logeado
            tvLogIn.text="Welcome: "+instanceAuth.currentUser?.email
        }else{
            tvLogIn.text="Apachurrale Ingresar"

        }
    }

    fun solicitarIngresarApp(){
        val providers= arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.cheems )
                .setTosAndPrivacyPolicyUrls(
                    "https://example.com/terms.html",
                    "https://example.com/privacy.html")
                .build(),
            COD_SESION_INICIO
        )
    }

    fun logOut(){
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener{
                Log.i("fb-loginh","Getting Out")
                val tvLogIn=findViewById<TextView>(R.id.tv_login)
                tvLogIn.text="No logeado"
            }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            COD_SESION_INICIO->{
                if(resultCode==Activity.RESULT_OK){
                    val user=IdpResponse.fromResultIntent(data)
                    val tvLogIn=findViewById<TextView>(R.id.tv_login)
                    tvLogIn.text="Welcome ${user?.email}"
                }else{
                    Log.i("fb-login","User Cancelled")

                }
            }
        }
    }
/*
    override fun onResume() {
        super.onResume()

        val tvLogIn=findViewById<TextView>(R.id.tv_login)
        val instanceAuth=FirebaseAuth.getInstance()
        if(instanceAuth.currentUser!=null){
            //Ya esta logeado
            tvLogIn.text="Welcome: "+instanceAuth.currentUser?.email
        }else{
            tvLogIn.text="Apachurrale Ingresar"
        }
    }
*/
}