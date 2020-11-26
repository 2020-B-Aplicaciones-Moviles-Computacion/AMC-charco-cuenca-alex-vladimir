import java.util.ArrayList

fun main(){
    println("Clase 05 - Nov-25")

    val arreglodin = arrayListOf<Int>(1,2,3,4,5,10,12,13)

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

    val respuestaAny:Boolean = arreglodin
            .any{
                valorActualIter ->
                    return@any valorActualIter>5
            }
    println(respuestaAny)

    //implementacion de OR
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

    //Fold - Desde el principio
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

    val ejemploUno = Suma(1,2,3)
    val ejemploDos = Suma(1,null,3)
    val ejemploTre = Suma(null,null,null)

    println(ejemploUno.sumar())
    println(Suma.histSumas)
    println(ejemploDos.sumar())
    println(Suma.histSumas)
    println(ejemploTre.sumar())
    println(Suma.histSumas)


    println("Valor Vida Actual: ${vidaAct}")

    //CLASES
    //metodo con Java
    abstract class numJava{
        protected val numUno:Int
        private val numDos:Int
        //Iniciar esos valores con el constructor
        //NO EXISTEN LOS ESTATICOS
        constructor(uno:Int,dos:Int){
            //blqoue de codigo primario
            numUno=1
            numDos=2
            println(numUno)
            println(numDos)

        }
    }

    //Con kotlin puedo definir de entrada los valores de numeros
    abstract class num(protected  var numUno:Int,  //constructor primario
                       protected var numDos:Int){
        init {
            //bloque de codigo primario
            println(numUno)
            println(numDos)

        }

    }

    class Suma(
         uno:Int,
         dos:Int,
        protected var tres:Int
        //Si no uso protected, no existen porque son parametors
        //si anado la palabra protected, defino que son valores de la clase
    ):num(uno,dos){//este es el superconstructor par valores de lo qu eestamos heredando
        //Primer Constructor
        init {
            println("Constructor primario init")
        }

        //Segndo Constructor
        constructor(
                //Parametros
                uno:Int?,dos:Int?,tres:Int?
        ):this(//Llamada al constructor primario
                if(uno==null) 0 else uno,
                if(dos==null)0 else dos,
                if(tres==null)0 else tres,

                ){

        }

        //Tercer Constructor
        constructor(
                //Parametros
                uno:Int,dos:Int?,tres:Int
        ):this(//Llamada al constructor primario
                uno,
                if(dos==null)0 else dos,
                tres
        ){

        }


        public fun sumar():Int{
            this.tres
            val total: Int = this.numDos+this.numUno
            Suma.addHist(total)
            return total
        }

        //companion objetc, metodos y atirbutos estaticos de una clase
        companion object{//singleton, metodos y atributos
        val histSumas = arrayListOf<Int>()

            fun addHist (newSum:Int){
                this.histSumas.add(newSum)
            }
        }

    }



}

class database(){
    companion object{
        val datos = arrayListOf<Int>()
    }
}