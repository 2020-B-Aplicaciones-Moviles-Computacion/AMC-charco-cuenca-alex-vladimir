package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val CODIGO_ACTUALIZAR_DATOS=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("main1","oncreate")

        //boton intent con respuesta
        val btnIntentResp = findViewById<Button>(R.id.btn_ir_intent_con_respuesta)
        btnIntentResp.setOnClickListener{
            irActividad(CIntentRespuesta::class.java)
        }

        //boton de ciclo de vida
        val btCicloVida = findViewById<Button>(R.id.button_ir_ciclo_vida)
        btCicloVida.setOnClickListener{
            irActividad( A_lifecycle::class.java)
        }

        //boton de listview
        val btListView = findViewById<Button>(R.id.btn_ir_list_view)
        btListView.setOnClickListener{
            irActividad(BListView::class.java)
        }

        //Boton intent
        val btnIntentExpl=findViewById<Button>(R.id.btn_ir_intent_explicito_parametros)
        btnIntentExpl.setOnClickListener{
           /* val intentExpl=Intent(
                    this,
                    CIntentExplicitParameters::class.java
            )*/
            val lig=liga("Rosa","Golden Medal")
            val entrenador = BEntrenador("Ash","Pueblo Paleta",lig)
            val param= arrayListOf<Pair<String,*>>(
                Pair("nombre","Alex"),
                Pair("apellido","Charco"),
                Pair("edad",22),
                Pair("ash",entrenador)

                )


            irActividad(
                CIntentExplicitParameters::class.java,
                param,
                CODIGO_ACTUALIZAR_DATOS
            )
        }

        val btnGoRecycleView = findViewById<Button>(
            R.id.btn_go_recycle_view
        )
        btnGoRecycleView
            .setOnClickListener{
                irActividad(
                    GRecycleView::class.java
                )
            }



        //final de onCreate
        /*
        BASE DE DATOS COMENTADA, ARREGLAR PARA DESCOMENTAR
        database.tableUser=SqliteHelUser(this)
        val userFound=database.tableUser?.consUserId(1)
        Log.i("db-query","Id:${userFound?.id}, Name:${userFound?.name},Desc: ${userFound?.desc}")
        if(userFound?.id==0){
            val result = database.tableUser?.createUserForm("Cosme","Fulanito")
            if(result!=null){
                if(result){
                    Log.i("db-query","Created")
                }else{
                    Log.i("db-query","Error Found")
                }
            }
        }else{
            val result2=database.tableUser
                    ?.updateUserForm(
                            "Alexander",
                            Date().time.toString(),
                            1
                    )
            if(result2!=null){
                if(result2){
                    Log.i("db-query","Updated")
                }else{
                    Log.i("db-query","Error Found")
                }

            }
        }
*/
    }

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
                tipoDato=it.second is Int

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Int)
                }
                tipoDato=it.second is Parcelable

                if(tipoDato==true){
                    intentEx.putExtra(nombreVar,valorVar as Parcelable)
                }



            }
        }

        if(codigo!=null){
            startActivityForResult(intentEx,codigo)
        }else{
            startActivity(intentEx)
        }



    }

    override fun onActivityResult(
            requestCode: Int, //Codigo de peticion - por el usuario
            resultCode: Int, //Codigo reslado - OK, CNACELAD
            data: Intent? //OPcional, data
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            102->{
                if(resultCode== RESULT_OK){
                    Log.i("Intent-Explicit","User updated data")
                    if(data!=null){
                        Log.i("Intent-Explicit","${data.getStringExtra("nombre")}," +
                                "${data.getIntExtra("edad",0)}")
                    }
                }else{
                    Log.i("Intent-Explicit","User not fulfilled data")
                }
            }

        }
    }


    override fun onDestroy(){
        super.onDestroy()
        Log.i("main1","ondestroy")
    }

/*

Antiguo metodo de acceso, ahora automatizado con una funcion, que recibe la clase
    fun irLifeCycle(){
        val intentExplicito = Intent(
            this,
            A_lifecycle::class.java
        )
        startActivity(intentExplicito)
    }
*/


}