package com.example.a02_android

class BEntrenador(var name: String,var desc: String) {

    override fun toString(): String {
        return "Entrenador: ${this.name}, Descripcion: ${this.desc}"
    }



}
/*

class BEntrenador(
        var nombre:String,
        var  descripcion:String
){
    override fun toString(): String {
        return "Nombre: ${this.nombre} Descripcion: ${descripcion}"
    }
}


 */