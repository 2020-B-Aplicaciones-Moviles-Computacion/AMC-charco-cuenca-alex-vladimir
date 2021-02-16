package com.example.firebase_assistant.DTO

import com.google.firebase.Timestamp
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class FirestoreOrderDTO(
    var nombre:String,
    var restaurante:HashMap<*,*>,
    val review:Int,
    var tiposComida:ArrayList<String>,
    var usuario:HashMap<*,*>,
    var fechaCreacion: Timestamp?
) {
}