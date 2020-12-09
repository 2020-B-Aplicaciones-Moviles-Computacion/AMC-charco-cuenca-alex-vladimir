package com.example.a02_android

class dbMemory {
  //Lo ideal es llenarlo alguna vez con itemas de basededatos, peticion de servidor u otro

    companion object{
        val arrayString = arrayListOf<BEntrenador>()
    }

        fun carga1(){
            arrayString.add(BEntrenador("Entrenador 1","Descripcion 1"))
            arrayString.add(BEntrenador("Entrenador 1","Descripcion 1"))
            arrayString.add(BEntrenador("Entrenador 1","Descripcion 1"))

        }


        //Funcion de carga de datos al arreglo
       /* fun cargaInicialDatos(){
            arrayString.add("")
            arrayString.add(3)
            arrayString.add(4)
            arrayString.add(5)
            arrayString.add(6)
            arrayString.add(7)

        }
*/
        /*
        fun cargaExterna(arrayList: ArrayList<String>,arrayList2: ArrayList<String>){

                    arrayList.map {valit->
                        arrayList2.map { valit2->
                            arrayString.add(valit+", "+valit2)
                        }
                    }
        }
*/




}