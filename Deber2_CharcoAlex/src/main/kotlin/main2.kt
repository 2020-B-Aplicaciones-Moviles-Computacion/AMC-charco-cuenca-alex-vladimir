import java.io.File
import java.util.*

fun main(args: Array<String>) {

    val dbLib = File("/home/alexander/Desktop/dbLibro.txt")
    val dbAut = File("/home/alexander/Desktop/dbAutor.txt")

    //myFile.appendText("\n" + "xd")
    val ar=myFile.readLines()

    var d = Date(2020, 11, 10)
    var arrayAutor = arrayListOf<autor>()

    arrayAutor.add(autor("cosme", 12, 2.5f, true, d))
    arrayAutor.add(autor("cosm", 13, 2.5f, true, d))
    arrayAutor.add(autor("cos", 14, 2.5f, true, d))
    arrayAutor.add(autor("co", 15, 2.5f, true, d))

    var t=0
    var size=arrayAutor.size
    while (t!=size){
        var text=arrayAutor[t].toString()+" \n"
        myFile.appendText(text)
        t++
    }

    myFile.writeText("")

}

