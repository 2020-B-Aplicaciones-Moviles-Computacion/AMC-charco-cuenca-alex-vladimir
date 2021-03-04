package com.example.firebase_assistant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebase_assistant.DTO.FirestoreUserDTO
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
         //   showRoles()
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
                showRoles()
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
                    if(user?.isNewUser==true){
                        //Logica crear usuario en coleccion
                        val db= Firebase.firestore

                        val rolUsuario= arrayListOf("usuario")
                        val newUser= hashMapOf<String,Any>(
                                "roles" to arrayListOf("usuario")
                        )

                        val userId=user.email.toString()

                        db.collection("usuario")
                                .document(userId)
                                .set(newUser)
                                .addOnSuccessListener {
                                    AuthUser.user?.roles=rolUsuario
                                    Log.i("fb-firestore","Created New User in DB")
                                }
                                .addOnFailureListener {
                                    Log.i("fb-firestore","Failled")
                                }
                    }

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
                localUser.email!!,
                    null
            )
            AuthUser.user=userFirebase
            cargarRoles(localUser.email!!)
        }
    }

    fun cargarRoles(uid:String){
        val db=Firebase.firestore
        val referencia = db.collection("usuario")
                .document(uid)
        referencia
                .get()
                .addOnSuccessListener {
                    val firestoreUser=it.toObject(FirestoreUserDTO::class.java)
                    AuthUser.user?.roles=firestoreUser?.roles
                    showRoles()
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Failled Recovering Roles")
                }

    }

    fun showRoles(){
        var txtRoles="Roles: "
        AuthUser.user?.roles?.forEach {
           txtRoles=txtRoles+","+it
        }
        val tvRoles=findViewById<TextView>(R.id.tv_roles)
        tvRoles.text=txtRoles
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














