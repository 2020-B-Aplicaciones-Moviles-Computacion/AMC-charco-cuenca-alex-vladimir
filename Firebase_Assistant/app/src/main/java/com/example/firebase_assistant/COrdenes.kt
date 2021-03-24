package com.example.firebase_assistant

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import com.example.firebase_assistant.DTO.FirestoreRestaurantDTO
import com.example.firebase_assistant.DTO.FirestoreUserOrdenDTO
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class COrdenes : AppCompatActivity() {

    val arreglo= arrayListOf<FirestoreRestaurantDTO>()
    var adapter:ArrayAdapter<FirestoreRestaurantDTO>?=null
    var arrayFoodType= arrayListOf<String>()
    var restSelected:FirestoreRestaurantDTO?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_ordenes)

        //botones
        val btnAddFood=findViewById<Button>(R.id.btn_addfoodtype)
        val btnAddOrder=findViewById<Button>(R.id.btn_addorder)

        btnAddFood.setOnClickListener {
            addFoodType()
        }

        btnAddOrder.setOnClickListener {
            createOrder()
            cleangui()
        }


        if(adapter==null){
            adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,arreglo)
            adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        deleteConsulta()
       }

    //CLASE 35

    //borrar documento en una coleccion, subcoleccion
    //de una consulta hecha, se realize consulta de review mayor a 3, elimine esos documentos
    fun deleteConsulta(){
        val db=Firebase.firestore
        val docRef=db.collection("orden")

        docRef
            .whereGreaterThanOrEqualTo("review",3)
            .get()
            .addOnSuccessListener {
                for(city in it){
                    Log.i("3fb-firestore","${city.id}")

                    //Deletion
                    docRef.document(city.id)
                        .delete()
                }
            }
            .addOnFailureListener {
                Log.i("3fb-firestore","Cities not Recovered")
            }
    }

    fun deleteDocument(){
        val db=Firebase.firestore
        val docRef=db.collection("cities")
            .document("BJ")
            .collection("landmarks")
            .document(    "FJygc9EylusOVgrbvRbl")
            .delete()
      .addOnSuccessListener {
          Toast.makeText(this,"Deleted", LENGTH_SHORT).show()
      }
      .addOnFailureListener {

          Toast.makeText(this,"Failed Deletion", LENGTH_SHORT).show()
      }
    }

    fun deleteOrder(){
        val db=Firebase.firestore
        val docRef=db.collection("cities")
            .document("BJ")
            .collection("landmarks")
            .document(    "FJygc9EylusOVgrbvRbl")

        val eliminarCampo= hashMapOf<String,Any>(
            "name" to FieldValue.delete()
        )

        docRef.update(eliminarCampo)
            .addOnSuccessListener {
                Toast.makeText(this,"${it}", LENGTH_SHORT).show()
            }
            .addOnFailureListener {

                Toast.makeText(this,"Failed Deletion", LENGTH_SHORT).show()
            }
    }


    //CLASE 32
    fun searchPaginated(){
        val db=Firebase.firestore
        val reference=db.collection("orden")

        reference
                .limit(2)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        Log.i("4fb-firestore","1: ${it.data}")
                    }

                    val last=it.last()

                    reference
                            .limit(2)
                            .startAfter(last)
                            .get()
                            .addOnSuccessListener {
                                it.forEach {
                                    Log.i("4fb-firestore","2: ${it.data}")
                                }
                            }
                            .addOnFailureListener {
                                Log.i("4fb-firestore","Error")
                            }

                }
                .addOnFailureListener {
                    Log.i("4fb-firestore","Nothing Recovered")
                }
    }

    //CLASE 31 -
    fun createDataGroupCollection(){
        val db=Firebase.firestore
        val cities = db.collection("cities")


        val data1 = hashMapOf(
                "name" to "San Francisco",
                "state" to "CA",
                "country" to "USA",
                "capital" to false,
                "population" to 860000,
                "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
                "name" to "Los Angeles",
                "state" to "CA",
                "country" to "USA",
                "capital" to false,
                "population" to 3900000,
                "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
                "name" to "Washington D.C.",
                "state" to null,
                "country" to "USA",
                "capital" to true,
                "population" to 680000,
                "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
                "name" to "Tokyo",
                "state" to null,
                "country" to "Japan",
                "capital" to true,
                "population" to 9000000,
                "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
                "name" to "Beijing",
                "state" to null,
                "country" to "China",
                "capital" to true,
                "population" to 21500000,
                "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    fun createDataGroupCollectionPark(){
        val db=Firebase.firestore
        val citiesRef = db.collection("cities")

        val ggbData = mapOf(
                "name" to "Golden Gate Bridge",
                "type" to "bridge"
        )
        citiesRef.document("SF").collection("landmarks").add(ggbData)

        val lohData = mapOf(
                "name" to "Legion of Honor",
                "type" to "museum"
        )
        citiesRef.document("SF").collection("landmarks").add(lohData)

        val gpData = mapOf(
                "name" to "Griffth Park",
                "type" to "park"
        )
        citiesRef.document("LA").collection("landmarks").add(gpData)

        val tgData = mapOf(
                "name" to "The Getty",
                "type" to "museum"
        )
        citiesRef.document("LA").collection("landmarks").add(tgData)

        val lmData = mapOf(
                "name" to "Lincoln Memorial",
                "type" to "memorial"
        )
        citiesRef.document("DC").collection("landmarks").add(lmData)

        val nasaData = mapOf(
                "name" to "National Air and Space Museum",
                "type" to "museum"
        )
        citiesRef.document("DC").collection("landmarks").add(nasaData)

        val upData = mapOf(
                "name" to "Ueno Park",
                "type" to "park"
        )
        citiesRef.document("TOK").collection("landmarks").add(upData)

        val nmData = mapOf(
                "name" to "National Musuem of Nature and Science",
                "type" to "museum"
        )
        citiesRef.document("TOK").collection("landmarks").add(nmData)

        val jpData = mapOf(
                "name" to "Jingshan Park",
                "type" to "park"
        )
        citiesRef.document("BJ").collection("landmarks").add(jpData)

        val baoData = mapOf(
                "name" to "Beijing Ancient Observatory",
                "type" to "musuem"
        )
        citiesRef.document("BJ").collection("landmarks").add(baoData)
    }

    fun searchlandmark(){
        val db=Firebase.firestore
        val referencia=db.collectionGroup("landmarks")
        referencia
                .whereEqualTo("type", "park")
                .get()
                .addOnSuccessListener {
                    for(city in it){
                        Log.i("3fb-firestore","${city.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("3fb-firestore","Cities not Recovered")
                }
    }

    //CLASE 30 - Busquedas

    //Equals to
    fun searchEqual(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("review",2)
                .get()
                .addOnSuccessListener {
                    for(orden in it){
                        Log.i("2fb-firestore","Recovered Order: ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("2fb-firestore","Error")
                }
    }

    //EqualTo, EqualTo, o + (tambien array-contains)
    fun searchTwoEquals(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("restaurante.nombre","LeRatatouille")
                .whereEqualTo("review",3)
                .get()
                .addOnSuccessListener {
                    for(orden in it){
                        Log.i("fb-firestore","Recovered Order: ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //EqualsTo, ArrayContains
    fun searchEqualsArrayContains(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("restaurante.nombre","LeRatatouille")
                .whereArrayContains("tiposComida","Francesa")
                .get()
                .addOnSuccessListener {
                    for(orden in it){
                        Log.i("fb-firestore","Recovered Order: ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //EqualsTo, Greatherthanequals
    fun searchGreaterthanEqualto(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("restaurante.nombre","LeRatatouille")
                .whereGreaterThanOrEqualTo("review",4)
                .get()
                .addOnSuccessListener {
                    Log.i("fb-firestore","Index Sucessful: ${it.isEmpty}")
                    it.forEach {
                        Log.i("fb-firestore","Recovered Order: ${it.data}")
                    }


                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //EqualsTo, Greaterthanequlas + OrderBy
    fun searchGreaterthanEqualtoOrderby(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("restaurante.nombre","LeRatatouille")
                .whereGreaterThanOrEqualTo("review",4)
                .orderBy("review",Query.Direction.DESCENDING)
//                .orderBy("restaurante.nombre",Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    it.forEach {
                        Log.i("fb-firestore","Recovered Order: ${it.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //Equalsto, arrayContainsAny
    fun searchEqualsArraycontainsany(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereEqualTo("restaurante.nombre","LeRatatouille")
                .whereArrayContainsAny("tiposComida", arrayListOf("Francesa"))//Solo en Arreglos
                .get()
                .addOnSuccessListener {
                    Log.i("fb-firestore","Recovered Order: ${it.isEmpty}")
                    for(orden in it){
                        Log.i("fb-firestore","Recovered Order: ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //Equalsto, WhereNotIn
    fun searchInNotin(){
        val db=Firebase.firestore
        val referencia=db.collection("orden")
        referencia
                .whereIn("restaurante.nombre", arrayListOf("LeRatatouille"))
                .whereNotIn("tiposComida", arrayListOf("ecuatoriana"))
                .get()
                .addOnSuccessListener {
                    for(orden in it){
                        Log.i("fb-firestore","Recovered Order: ${orden.data}")
                    }
                }
                .addOnFailureListener {
                    Log.i("fb-firestore","Error")
                }
    }

    //CLASE 29
    fun createOrder(){
        if(restSelected!=null && FirebaseAuth.getInstance()!=null){
            val rest=restSelected
            val instanceAuth= FirebaseAuth.getInstance()
            val usuario=FirestoreUserOrdenDTO(instanceAuth.currentUser?.uid.toString())
            val etTextReviw=findViewById<EditText>(R.id.et_review)

            val newOrder= hashMapOf<String,Any?>(
                    "restaurante" to rest,
                    "usuario" to usuario,
                    "review" to etTextReviw.text.toString().toInt(),
                    "tiposComida" to arrayFoodType,
                    "fechaCreacion" to Timestamp(Date())
            )

            val db= Firebase.firestore
            val referencia=db.collection("orden")
                    .document()
                    .set(newOrder)
            referencia
                    .addOnSuccessListener {

                        Toast.makeText(this,"Order Created",LENGTH_SHORT).show()
                    }
                    .addOnFailureListener{

                        Toast.makeText(this,"Order Not Created",LENGTH_SHORT).show()
                    }
        }

    }

    fun cleangui(){
        val te_food=findViewById<EditText>(R.id.et_foodtype)
        val te_review=findViewById<EditText>(R.id.et_review)
        te_food.setText("")
        te_review.setText("")
    }

    fun cargarRestaurantes(){
        val spRestaurante=findViewById<Spinner>(R.id.sp_restaurante)
        spRestaurante.adapter=adapter
        spRestaurante.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                restSelected=arreglo[position]
            }

            override fun onNothingSelected(parent:AdapterView<*>?){
                Log.i("fb-firestore","Nothing Selected")
            }
        }

        val db= Firebase.firestore
        val referencia = db.collection("restaurantes")
        referencia
                .get()
                .addOnSuccessListener {
                    for(document in it){
                        val rest=document.toObject(FirestoreRestaurantDTO::class.java)
                        rest.uid=document.id
                        arreglo.add(rest)
                    }
                    adapter?.notifyDataSetChanged()
                    Toast.makeText(this, "Recovered", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {

                }

    }

    fun addFoodType(){
        val etFoodType=findViewById<EditText>(R.id.et_foodtype)
        val text=etFoodType.text.toString()
        arrayFoodType.add(text)
        val tvFoddType=findViewById<TextView>(R.id.tv_foodtype)
        val txtAnterior=tvFoddType.text.toString()
        tvFoddType.setText("${txtAnterior},${text}")
        etFoodType.setText("")

    }


}