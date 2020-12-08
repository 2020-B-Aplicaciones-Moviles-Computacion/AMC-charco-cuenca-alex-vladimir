import java.io.File
import java.io.FileWriter
import java.util.*

class databaseLA {


    companion object memautor {
        val dbLib = File("/home/alexander/Desktop/dbLibro.txt")
        val dbAut = File("/home/alexander/Desktop/dbAutor.txt")

        var arrayAutor = arrayListOf<autor>()
        var arrayBook = arrayListOf<libro>()

        fun cargaInicial() {
            var d = Date(2020, 11, 10)
            arrayAutor.add(autor("cosme", 12, 2.5f, true, d))



        }


        fun readA() {
            print(databaseLA.arrayAutor)
        }

        fun readL() {
            print(databaseLA.arrayBook)
        }


        //READ
        //Lectura de Autor
        //id
        fun readbyIdA(idpar: Int) {

            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getID() == idpar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //nombre
        fun readbyNombreA(nombrepar: String) {

            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getNombre() == nombrepar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //edad
        fun readbyEdadA(edadpar: Int) {
            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getEdad() == edadpar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //fecha nacimiento
        fun readbyFnacA(fnacpar: Date) {
            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getFnac() == fnacpar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //peso
        fun readbyPesoA(pesopar: Float) {
            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getPeso() == pesopar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //vivo
        fun readbyVivoA(vivopar: Boolean) {
            var cons: String = "No existe"
            databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getVivo() == vivopar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //Lectura de Libro
        //id
        fun readbyIdL(idpar: Int) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getID() == idpar) {
                    cons = databaseLA.arrayAutor[ind].toString()
                }
            }
            print(cons)
        }

        //nombre
        fun readbyNombreL(nombrepar: String) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getNombre() == nombrepar) {
                    cons = databaseLA.arrayBook[ind].toString()
                }
            }
            print(cons)
        }

        //Hojas
        fun readbyHojasL(hojaspar: Int) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getHojas() == hojaspar) {
                    cons = databaseLA.arrayBook[ind].toString()
                }
            }
            print(cons)
        }

        //Puntuacion
        fun readbyPuntL(par: Float) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getPunt() == par) {
                    cons = databaseLA.arrayBook[ind].toString()
                }
            }
            print(cons)
        }

        //Disponible
        fun readbyDispL(par: Boolean) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getDisp() == par) {
                    cons = databaseLA.arrayBook[ind].toString()
                }
            }
            print(cons)
        }

        //FechaPublicacion
        fun readbyFpubL(par: Date) {
            var cons: String = "No existe"
            databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getFpub() == par) {
                    cons = databaseLA.arrayBook[ind].toString()
                }
            }
            print(cons)
        }

        //UPDATE
        fun updateA() {
            var des: String
            val read = Scanner(System.`in`)
            print("Escoja el ID de la entrada a actualizar")
            val ina: Int = read.nextInt()

            println("Setear Nombre?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: String = readLine()!!
                databaseLA.arrayBook[ina].setNombre(par)
                des = "0"
            }
            println("Setear Hojas?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: Int = read.nextInt()
                databaseLA.arrayBook[ina].setHojas(par)
            }
            println("Setear Puntuacion?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: Float = read.nextFloat()
                databaseLA.arrayBook[ina].setPunt(par)
            }
            println("Setear Disponibilidad?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: Boolean = read.nextBoolean()
                databaseLA.arrayBook[ina].setDisp(par)
            }
            println("Setear Fecha de Publicacion?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val pary: Int = read.nextInt()
                val parm: Int = read.nextInt()
                val pard: Int = read.nextInt()
                val parD = Date(pary, parm, pard)
                databaseLA.arrayBook[ina].setFpub(parD)
            }
            println("Setear Autor?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: Int = read.nextInt()
                databaseLA.arrayBook[ina].setFk(par)
            }
            var t = 0
            var size = arrayAutor.size
            dbLib.writeText("")
            while (t != size) {
                var text = arrayAutor[t].toString() + " \n"
                dbAut.appendText(text)
                t++

            }
        }


        fun updateL() {
            var des: String
            val read = Scanner(System.`in`)
            print(databaseLA.arrayAutor)
            print("Escoja el ID de la entrada a actualizar")
            val ina: Int = read.nextInt()
            println("Setear Nombre?:\n1.Si")
            des = readLine()!!
            if (des == "1") {
                val par: String = readLine()!!
                databaseLA.arrayAutor[ina].setNombre(par)
                des = "0"
            }
            println("Setear Edad?:\n")
            des = readLine()!!
            if (des == "1") {
                val par: Int = read.nextInt()
                databaseLA.arrayAutor[ina].setEdad(par)
            }
            println("Setear Peso?:\n")
            des = readLine()!!
            if (des == "1") {
                val par: Float = read.nextFloat()
                databaseLA.arrayAutor[ina].setPeso(par)
            }
            println("Setear Vivo?:\n")
            des = readLine()!!
            if (des == "1") {
                val par: Boolean = read.nextBoolean()
                databaseLA.arrayAutor[ina].setVivo(par)
            }
            println("Setear Fecha Nacimiento?:\n")
            des = readLine()!!
            if (des == "1") {
                val pary: Int = read.nextInt()
                val parm: Int = read.nextInt()
                val pard: Int = read.nextInt()
                val parD = Date(pary, parm, pard)
                databaseLA.arrayAutor[ina].setFnac(parD)
            }

            var t = 0
            var size = arrayBook.size
            dbLib.writeText("")
            while (t != size) {
                var text = arrayBook[t].toString() + " \n"
                dbLib.appendText(text)
                t++

            }
        }


        //DELETE
        fun deleteA() {
            println(arrayAutor)
            val read = Scanner(System.`in`)
            println("Seleccione ID:\n")
            val idb: Int = read.nextInt()

            val xd = databaseLA.arrayAutor.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayAutor[ind].getID() == idb) {
                    databaseLA.arrayAutor.removeAt(ind)
                }
            }

            var t=0
            var size=arrayAutor.size
            dbLib.writeText("")
            while (t!=size){
                var text=arrayAutor[t].toString()+" \n"
                dbAut.appendText(text)
                t++
            }

        }

        fun deleteL() {
            val read = Scanner(System.`in`)
            println("Seleccione ID:\n")
            val idb: Int = read.nextInt()
            val xd = databaseLA.arrayBook.forEachIndexed() { ind, valit ->
                if (databaseLA.arrayBook[ind].getID() == idb) {
                    databaseLA.arrayBook.removeAt(ind)
                }
            }
            var t=0
            var size=arrayBook.size
            dbLib.writeText("")
            while (t!=size){
                var text=arrayBook[t].toString()+" \n"
                dbLib.appendText(text)
                t++
            }

        }

        //Imprimir toda base de datos
        fun dbprintA() {
            print(arrayAutor)
        }

        fun dbprintL() {
            print(arrayBook)
        }

        //SELECCION POR PARAMETROS
        fun searchA() {
            val read = Scanner(System.`in`)
            println("Escoja el parametro de busqueda:\n1.ID\n2.Nombre\n3.Fecha Nacimiento\n4.Edad\n5.Peso\n6.Vivo\n?:")
            var par: Int = read.nextInt()
            when (par) {
                1 -> {
                    print("Ingrese valor de ID:")
                    var par: Int = read.nextInt()
                    databaseLA.readbyIdA(par)
                }
                2 -> {
                    print("Ingrese valor de Nombre:")
                    var par: String = readLine()!!
                    databaseLA.readbyNombreA(par)
                }
                3 -> {
                    print("Fecha Nacimiento: \n  Ano: ")
                    var y: Int = read.nextInt()
                    print("  Mes: ")
                    var m: Int = read.nextInt()
                    print("  Dia: ")
                    var d: Int = read.nextInt()
                    var par = Date(y, m, d)
                    databaseLA.readbyFnacA(par)
                }
                4 -> {
                    print("Ingrese valor de Edad:")
                    var par: Int = read.nextInt()
                    databaseLA.readbyEdadA(par)
                }
                5 -> {
                    print("Ingrese valor de Peso:")
                    var par: Float = read.nextFloat()
                    databaseLA.readbyPesoA(par)
                }
                6 -> {
                    print("Ingrese valor de Vivo:")
                    var par: Boolean = read.nextBoolean()
                    databaseLA.readbyVivoA(par)
                }
                else -> {
                    println("Ingrese a una opcion valida")
                }
            }

        }


        fun searchL() {
            val read = Scanner(System.`in`)
            println("Escoja el parametro de busqueda:\n1.ID\n2.Nombre\n3.Hojas\n4.Puntuacion\n5.Disponibilidad\n6.Fecha Publicacion\n?:")
            var par: Int = read.nextInt()
            when (par) {
                1 -> {
                    print("Ingrese valor de ID:")
                    var par: Int = read.nextInt()
                    databaseLA.readbyIdL(par)
                }
                2 -> {
                    print("Ingrese valor de Nombre:")
                    var par: String = readLine()!!
                    databaseLA.readbyNombreL(par)
                }
                3 -> {
                    print("Ingrese valor de Hojas")
                    var par: Int = read.nextInt()
                    databaseLA.readbyHojasL(par)
                }
                4 -> {
                    print("Ingrese valor de Puntuacion:")
                    var par: Float = read.nextFloat()
                    databaseLA.readbyPuntL(par)
                }
                5 -> {
                    print("Ingrese valor de Disponibilidad:")
                    var par: Boolean = read.nextBoolean()
                    databaseLA.readbyDispL(par)
                }
                6 -> {
                    print("Fecha de Publicacion: \n  Ano: ")
                    var y: Int = read.nextInt()
                    print("  Mes: ")
                    var m: Int = read.nextInt()
                    print("  Dia: ")
                    var d: Int = read.nextInt()
                    var par = Date(y, m, d)
                    databaseLA.readbyFpubL(par)

                }


            }




        }

        fun writeA(par:String){
            val myFile = File("/home/alexander/Desktop/dbAutor.txt")
            myFile.appendText("\n" + par)

        }


        fun writeL(par:String){
            val myFile = File("/home/alexander/Desktop/dbLibro.txt")
            myFile.appendText("\n" + par)

        }


    }


}