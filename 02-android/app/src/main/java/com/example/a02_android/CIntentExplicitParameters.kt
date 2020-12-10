package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class CIntentExplicitParameters : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_intent_explicit_parameters)

        val nombre =  intent.getStringExtra("nombre")
        val apellido =  intent.getStringExtra("apellido")
        val edad =  intent.getIntExtra("edad",0)

        if(nombre!=null && apellido!=null &&edad!=null){
            Log.i("intent-explicito","${nombre},${apellido},${edad},")

        }

        val btnReturnPar = findViewById<Button>(R.id.btn_devolver_parametros)

        btnReturnPar.setOnClickListener{
            val modNom="Cosme "
            val modEda=25
            val intentResponse = Intent()
            intentResponse.putExtra("nombre",modNom)
            intentResponse.putExtra("edad",modEda)

            this.setResult(//DEvolucion de parametro a la actividad llamante
                    RESULT_OK,
                    intentResponse
            )

            this.finish()
        }


    }

}