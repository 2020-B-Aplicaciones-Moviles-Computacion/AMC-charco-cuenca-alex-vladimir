package com.example.a02_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class A_lifecycle : AppCompatActivity() {
    var total = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_lifecycle)





        Log.i("ciclo-vida","oncreate")




        val btSum=findViewById<Button>(R.id.btn_ciclo_vida)


        val txTotal=findViewById<TextView>(R.id.txv_ciclo_vida)


        btSum.setOnClickListener{
            total=total+1
            txTotal.text=total.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("ciclo-vida","onSaveInstance")
        if(outState!=null){
            outState.run {
                //Aqui es donde se ira a guardar las cosas
                //Solo se pueden guardar cosas como:
                //Guardar solo primiticos - int, bool, char, etc
                //Si queremos guardar clases no se podra por ahora
                //put -> tipo de dato que queramos
                putInt("totalGuardado",total)
                    //Se guarda como: llave-valor
                    //aunque pareciera que no se guardo nada, si esta almacenando por detras el valor de las variables


            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("ciclo-vida","onRestoreInstance")
        val recoverVal = savedInstanceState.getInt("totalGuardado")
        if(recoverVal!=null){
            total=recoverVal

            var txTotal = findViewById<TextView>(R.id.txv_ciclo_vida)
            txTotal.text=total.toString()
        }
    }


    override fun onStart(){
        super.onStart()
        Log.i("ciclo-vida","onstart")
    }

    override fun onResume(){
        super.onResume()
        Log.i("ciclo-vida","onresume")
    }

    override fun onPause(){
        super.onPause()
        Log.i("ciclo-vida","onpause")
    }

    override fun onStop(){
        super.onStop()
        Log.i("ciclo-vida","onstop")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.i("ciclo-vida","ondestroy")
    }

    override fun onRestart(){
        super.onRestart()
        Log.i("ciclo-vida","onstart")
    }


}