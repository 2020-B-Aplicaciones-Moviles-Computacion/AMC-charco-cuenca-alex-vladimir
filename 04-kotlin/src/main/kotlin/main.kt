import kotlin.collections.ArrayList


fun main(){

    println("Clase 05 - Nov-25")

    //Arreglo Estatico
    val arregloest:IntArray= intArrayOf(1,2,3)
    //Arreglo Dinamico
    val arreglodin:ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5,10,12,13)
    arreglodin.add(5)

    //Foreach
    arreglodin.forEach{
        itval->
            println("Value: ${itval}")
    }

    //ForEach Indexed
    arreglodin.forEachIndexed{
        index,itval->
        println("Value: ${itval} with index: ${index}")
    }

    //Map
    val arreglomap = arreglodin.map{
        valIt->
            return@map valIt*10
    }

    //Filter
    val arreglofil= arreglodin.filter{valIt->
        val higher5:Boolean =valIt>5
            return@filter higher5
    }

    arreglodin.filter { i-> i>5 }



    //Clase 05 - Miercoles 25 de Noviembre
    //En cualquier funcion el objeto de entrada a un funcion se llama 'it'
    arreglodin.filter{i -> i>5}


    //Operadores: ANY, ALL
    //aYUDA A REvisar una condicione dentro de un arreglo
    //utiliza las tablas de tautologias: OR, AND

    //Con any, usamos una expresion para iteracione n cada elemento de los arreglos
    //Como es any, si todos son falos, devolvera falsos
    //Caso contrario uno solo no falso -> verdadero
    println(arreglodin)


    //ANY
    val respuestaAny:Boolean = arreglodin
            .any{
                valorActualIter ->
                    return@any valorActualIter>5
            }
    println(respuestaAny)

    //ALL
    val respuestaAll:Boolean=arreglodin.all{
        valotIt ->
            return@all valotIt>5
    }


    println(respuestaAll)


    //REDUCE
    val reduceSum=arreglodin.reduce{//Valor de acumulado por defecto es 0
        acumulado, valorIt ->
            return@reduce acumulado+valorIt
    }



    println("Valor de reduccion: ${reduceSum}")

    //Fold
    val reduceFold = arreglodin.fold(
            75,{
                acum, valIt->
            return@fold acum - valIt
    }
    )



    println("Valor de reduceFold: ${reduceFold}")


    // arreglodin.foldRight - Eempieza desde el final, con valor de inicio
    // arreglodin.reduceRigth - Empieza desde el final en 0

    //OPERADORES
    //Develuveln difernetes cosas dependiendo de su funcion
    //foreach   Unit(void)
    //map       arreglo
    //fileter   arreglo
    //all       booleano
    //any       booleano
    //reduce    valor
    //fols      valor
        //Se puede hacer una concatenacion de dichos valores


    val vidaAct:Double = arreglodin
            .map { it*85 }
            .filter { it>10 }
            .fold(100.00,{acc,i->acc-i})
            .also{println(it)}


    //////////////////////////////////////////////////////////////////////////////////
    //CLASES
    println("\n______________________CLASES___________________________")
    val ejemplo1 = Sum(1,2,3)
    val ejemplo2 = Sum(1,null,3)
    val ejemplo3 = Sum(null,null,null)
    println("Suma1: ${ejemplo1.sumar()}")
    println(Sum.historialSum)
    println("Suma2: ${ejemplo2.sumar()}")
    println(Sum.historialSum)
    println("Suma3: ${ejemplo3.sumar()}")
    println(Sum.historialSum)


}

    abstract class numJ {
        protected val num1:Int
        private val num2:Int

        constructor(uno:Int,dos:Int) {
            num1=uno
            num2=dos
        }
    }

    abstract class Numbers(
            protected var num1:Int,
            protected var num2:Int){
        init{
            println(num1)
            println(num2)

        }
    }

    class Sum(
        uno:Int, dos:Int,protected var tres:Int
        //, cuatro:Int
    ):Numbers(uno,dos){
        init{
            this.num1
            this.num2
            this.sumar()
            println("Constructor Primario")
        }

        //Segundo Constructor
        constructor(uno:Int,dos: Int?,tres:Int  //Parametros
        ):this(//Llamada al constructo primario
                uno,if( dos==null)0 else dos,tres){

        }


        //Tercer Constructor
        constructor(uno:Int?,dos: Int?,tres:Int?  //Parametros
        ):this(//Llamada al constructo primario
                if( uno==null)0 else uno,
                if( dos==null)0 else dos,
                if( tres==null)0 else tres,
        ){

        }


        public fun sumar():Int{
            val total:Int=this.num1+this.num2
            Sum.addhis(total)
            return total
        }



        companion object{
            val historialSum = arrayListOf<Int>()
            fun addhis(newsum:Int){
                this.historialSum.add(newsum)
            }
        }



    }

class database(){
    companion object{
        val datos = arrayListOf<Int>()

    }
}

