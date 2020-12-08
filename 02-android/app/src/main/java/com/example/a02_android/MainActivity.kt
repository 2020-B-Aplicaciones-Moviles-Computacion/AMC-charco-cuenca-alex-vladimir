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
          //  irActividad( A_lifecycle::class.java)
        }

        //boton de listview
        val btListView = findViewById<Button>(R.id.btn_ir_list_view)
        btListView.setOnClickListener{
//            irActividad(BListView::class.java)
        }

        //Boton intent
        val btnIntentExpl=findViewById<Button>(R.id.btn_ir_intent_explicito_parametros)
        btnIntentExpl.setOnClickListener{
            val param= arrayListOf<ArrayList<*>>(
                arrayListOf("nombre","Alex"),
                arrayListOf("apellido","Charco"),
                arrayListOf("edad",22)

                )

            irActividad(btnIntentExpl::class.java,param)
        }


    }

    fun irActividad(
            clase:Class<*>,
            param:ArrayList<ArrayList<*>>?){
        val intentEx=Intent(
            this,
            clase
        )
        //for
        if(param!=null){
            param.forEach {
                var nombreVar = it[0]
                var valorVar:Any=it[1]

                intentEx.putExtra(nombreVar.toString(),valorVar.toString())

            }
        }

        startActivity(intentEx)

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