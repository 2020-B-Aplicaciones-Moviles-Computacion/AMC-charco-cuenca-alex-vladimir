import java.util.*

fun main(args: Array<String>) {
    println("Inicio de Sistema\n")



    var x:Int = 0
    var loop1:Int=1
    val read = Scanner(System.`in`)

    while(loop1==1){
        println("\nIngrese la operacion CRUD:\n1.Crear\n2.Leer\n3.Actualizar\n4.Borrar\n5.Salir\n?: ")
        x=read.nextInt()
        when (x) {
            1 ->{
                print("-------------------------CREACION-------------------------\n")
                print("Escoja el elemento a crear: \n1.Libro\n2.Autor\n")
                var c:Int=read.nextInt()
                when(c){
                    1->{

                        print("Autor ID: ")
                        val fk:Int=read.nextInt()

                        if( autor.id!=0){
                            print("Ingrese los datos de libro\n")
                            print("Nombre: ")
                            var v1:String= readLine()!!
                            print("Hojas: ")
                            var v2:Int=read.nextInt()
                            print("Puntuacion: ")
                            var v3:Float=read.nextFloat()
                            print("Disponible: ")
                            var v4:Boolean=read.nextBoolean()
                            print("Fecha Publicacion: \n  Ano: ")
                            var y:Int=read.nextInt()
                            print("  Mes: ")
                            var m:Int=read.nextInt()
                            print("  Dia: ")
                            var d:Int=read.nextInt()
                            var v5=Date(y,m,d)

                            val inputL=libro(v1,v2,v3,v4,v5,fk)
                            databaseLA.arrayBook.add(inputL)

                        }else{
                            println("Ingrese al menos un Autor")
                        }


                    }
                    2->{
                        print("Ingrese los datos de autor\n")
                        print("Autor: ")
                        var v1:String= readLine()!!
                        print("Edad: ")
                        var v2:Int=read.nextInt()
                        print("Peso: ")
                        var v3:Float=read.nextFloat()
                        print("Vivo: ")
                        var v4:Boolean=read.nextBoolean()
                        print("Fecha Nacimiento: \n  Ano: ")
                        var y:Int=read.nextInt()
                        print("  Mes: ")
                        var m:Int=read.nextInt()
                        print("  Dia: ")
                        var d:Int=read.nextInt()
                        var v5=Date(y,m,d)
                        val inputA=autor(v1,v2,v3,v4,v5)
                        databaseLA.arrayAutor.add(inputA)

                    }
                    else->{
                        println("Ingrese a una opcion valida")
                    }
                }

            }


            2 ->{
                print("-------------------------LECTURA--------------------------\n")
                print("Escoja el elemento a leer: \n1.Autor\n2.Libro\n")
                var c:Int=read.nextInt()
                when(c) {
                    1 -> {
                        println("Escoja el tipo de busqueda: \n1.Tabla Completa\n2.Autor por parametro\n")
                        var tb:Int=read.nextInt()
                        when(tb){
                            1->{
                                databaseLA.dbprintA()
                            }
                            2->{
                                databaseLA.searchA()
                            }
                            else->{
                                println("Ingrese a una opcion valida")
                            }
                        }

                    }
                    2->{

                        println("Escoja el tipo de busqueda: \n1.Tabla Completa\n2.Libro por parametro\n")
                        var tb:Int=read.nextInt()
                        when(tb){
                            1->{
                                databaseLA.dbprintL()
                            }
                            2->{
                                databaseLA.searchL()
                            }
                                    else->{
                                        println("Ingrese a una opcion valida")
                                    }
                                }
                            }
                            else->{
                                println("Ingrese a una opcion valida")
                            }
                        }


                    }





            3-> {
                print("-------------------------ACTULIZACION--------------------------\n")
                print("Escoja el elemento a actualizar: \n1.Autor\n2.Libro\n")
                var act:Int=read.nextInt()
                when(act) {
                    1->{
                        databaseLA.updateL()
                    }
                    2->{
                        databaseLA.updateA()
                    }
                    else->{
                        println("Ingrese una opcion valida")
                    }
                }
            }

            4 -> {
                print("-------------------------BORRADO-------------------------\n")
                print("Escoja el elemento a eliminar: \n1.Autor\n2.Libro\n")
                val des:String= readLine()!!
                when(des){
                    "1"->{
                        databaseLA.deleteA()
                    }
                    "2"->{
                        databaseLA.deleteL()
                        //xd

                    }
                    else->{
                        println("Ingrese una opcion valida")
                    }
                }

            }

            5 -> {
                print("Saliendo ")
                loop1=0
            }

            else -> { // Note the block
                print("Ingrese a una opcion valida")
            }}



    }



}
