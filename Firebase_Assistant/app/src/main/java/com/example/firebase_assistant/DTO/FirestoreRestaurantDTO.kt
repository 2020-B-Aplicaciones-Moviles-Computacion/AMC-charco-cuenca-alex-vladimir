package com.example.firebase_assistant.DTO

data class FirestoreRestaurantDTO(
    var nombre:String="",
    var uid:String=""
) {
    override fun toString():String{
        return nombre
    }
}