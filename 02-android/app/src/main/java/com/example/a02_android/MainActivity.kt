package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("main1","oncreate")

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
            val intentExpl=Intent(
                    this,
                    CIntentExplicitParameters::class.java
            )
            /*
            val param= arrayListOf<Pair<String,*>>(
                Pair("nombre","Alex"),
                Pair("apellido","Charco"),
                Pair("edad",22)

                )
                */

            Log.i("sms1","StartAct")
            startActivityForResult(intentExpl,102)
            //irActividad(btnIntentExpl::class.java,param)
        }


    }

    fun irActividad(

            clase:Class<*>,
            param:ArrayList<Pair<String,*>>?=null){
        Log.i("sms1","inAct")
        val intentEx=Intent(
            this,
            clase
        )
        //for
        if(param!=null){
            Log.i("sms1","inAct2")
            param.forEach {
                var nombreVar = it.first
                var valorVar=it.second is Any
                Log.i("sms1","inAct3")
                Log.i("sms1","${nombreVar},${valorVar}")
                intentEx.putExtra(nombreVar,valorVar)

            }
        }
        Log.i("sms1","inAct4")
        startActivity(intentEx)

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