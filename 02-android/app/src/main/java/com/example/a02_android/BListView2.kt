package com.example.a02_android
/*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

//        dbMemory.cargaExterna(b1.name,b1.desc)

        val adaptador = ArrayAdapter(
                this,//contexto
                android.R.layout.simple_list_item_1,  //layaour  (xml visual que existe en adroid)
                dbMemory.arrayString//lista de numeros
        )


        
        //cualquier cosa que se mostrara visualemnte y se itere, como listras
        //VA a necesitar un componente visual similar a listview, hay otros componentes a parte de este
        //El adaptador ayudara a definir como se vera visualmente dentro de la lsita que se usara

        val listView = findViewById<ListView>(R.id.lv_entrenador)

        listView.adapter=adaptador

        //para notificar los cambios en la lista, como aumentar algo dentro del arreglo,
        adaptador.notifyDataSetChanged()

        val btnAddLV = findViewById<Button>(R.id.btn_anadir_item_lv)

        btnAddLV.setOnClickListener{
            anadirListView(adaptador,BEntrenador("Entrenador X", "Descripcion X"),dbMemory.arrayString)
        }

        //dbMemory.arregloEnteros.add(5)
        //dbMemory.arregloEnteros.add(6)


    }

    fun anadirListView(
            adaptador: ArrayAdapter<BEntrenador>,
            item: BEntrenador,
            arreglo: ArrayList<BEntrenador>
    ){
        arreglo.add(item)
        adaptador.notifyDataSetChanged()
    }


}


 */