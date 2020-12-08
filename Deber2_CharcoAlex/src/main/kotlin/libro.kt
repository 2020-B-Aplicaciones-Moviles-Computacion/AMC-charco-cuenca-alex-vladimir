import java.util.*

class libro(private var nombre:String,private  var hojas:Int,private  var puntuacion:Float,
            private var disponible:Boolean, private var fpub:Date,private var fk:Int) {
    //Atributos
    var id:Int = 0


    //Constructor
    init{
        libro.id++
        this.id=libro.id
        println("\n....Insertado\n")
        databaseLA.writeL(toString())

    }

    //ToString
    override fun toString(): String {
        return "${id},${nombre},${hojas},${puntuacion},${disponible},${fpub},${fk}, "
    }

    //Getters
    fun getID():Int{
        return id
    }
    fun getNombre():String{
        return nombre
    }
    fun getHojas():Int{
        return hojas
    }
    fun getPunt():Float{
        return puntuacion
    }
    fun getDisp():Boolean{
        return disponible
    }
    fun getFpub():Date{
        return fpub
    }

    //Setters
    fun setFk(par:Int){
        this.fk=par
    }
    fun setNombre(par:String){
        this.nombre=par
    }
    fun setHojas(par:Int){
        this.hojas=par
    }
    fun setPunt(par:Float){
        this.puntuacion
    }
    fun setDisp(par:Boolean){
        this.disponible=par
    }
    fun setFpub(par:Date){
        this.fpub=par
    }

    companion object{
        var id:Int=0
    }

}