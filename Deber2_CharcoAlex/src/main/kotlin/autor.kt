import java.util.*

class autor (private var nombre:String,private  var edad:Int,private  var peso:Float,
             private  var vivo:Boolean,private  var fnac: Date) {

    var id:Int=0

    init{
        autor.id++
        this.id=autor.id

        println("\n....Insertado\n")
        databaseLA.writeA(toString())
    }

    override fun toString(): String {
        return "${id},${nombre},${edad},${peso},${vivo},${fnac},"
    }

    //Getters
    fun getID():Int{
        return id
    }
    fun getNombre():String{
        return nombre
    }
    fun getEdad():Int{
        return edad
    }
    fun getPeso():Float{
        return peso
    }
    fun getVivo():Boolean{
        return vivo
    }
    fun getFnac():Date{
        return fnac
    }

    //Setters
    fun setNombre(par:String){
        this.nombre=par
    }
    fun setEdad(par:Int){
        this.edad=par
    }
    fun setPeso(par:Float){
        this.peso=par
    }
    fun setVivo(par:Boolean){
        this.vivo=par
    }
    fun setFnac(par:Date){
        this.fnac=par
    }




    companion object{
        var id:Int=0
    }

}