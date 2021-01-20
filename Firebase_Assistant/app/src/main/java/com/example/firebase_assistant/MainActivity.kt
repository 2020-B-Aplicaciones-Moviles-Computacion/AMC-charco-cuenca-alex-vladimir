package com.example.firebase_assistant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val COD_SESION_INICIO=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngresar=findViewById<Button>(R.id.btn_ingresar);
        val btnSalir=findViewById<Button>(R.id.btn_salir);
        val btnClose=findViewById<Button>(R.id.btn_close);
        val btnFirestore=findViewById<Button>(R.id.btn_firestore)
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
        btnFirestore.setOnClickListener {
            irActividad(BFirestore::class.java)
        }

        val instanceAuth=FirebaseAuth.getInstance()
        if(instanceAuth.currentUser!=null){
            //Ya esta logeado
            tvLogIn.text="Welcome: "+instanceAuth.currentUser?.email
            setFirebaseUser()
            showHiddenButton()
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
                tvLogIn.text="Sucessful Log-out"

                AuthUser.user=null
                showHiddenButton()
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

                    setFirebaseUser()
                    showHiddenButton()
                }else{
                    Log.i("fb-login","User Cancelled")

                }
            }
        }
    }

    fun setFirebaseUser(){
        val instanceAuth=FirebaseAuth.getInstance()
        val localUser=instanceAuth.currentUser

        if(localUser!=null){
            val userFirebase=UserFirebase(
                localUser.uid,
                localUser.email!!
            )
            AuthUser.user=userFirebase
        }
    }

    fun showHiddenButton(){
        val btnFirestore = findViewById<Button>(R.id.btn_firestore)
        if(AuthUser.user!=null){
            btnFirestore.visibility= View.VISIBLE
        }else{
            btnFirestore.visibility= View.INVISIBLE
        }
    }

    //Ir Actividad
    fun irActividad(

        clase:Class<*>,
        param:ArrayList<Pair<String,*>>?=null,
        codigo:Int? =null
    ){
        val intentEx=Intent(
            this,
            clase
        )

        if(param!=null){
            param.forEach {
                var nombreVar = it.first
                var valorVar=it.second

                var tipoDato=false

                tipoDato=it.second is String

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as String)
                }




            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }



    }



    }














