package com.example.a02_android

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelUser (
        contexto: Context?
):SQLiteOpenHelper(
        contexto,
        "moviles",
        null,
        1
){
    override fun onCreate(db: SQLiteDatabase?) {
        val scCreatetbUser=
                """ Create table user(
                    |id integer primary key autoincrement,
                    |name varchar(50),
                    |description varchar(50)
                    |)""".trimMargin()

        Log.i("db","Creatrint user table")
        db?.execSQL(scCreatetbUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun consUserId(
            id:Int
    ):userdb{
        val scriptConUser ="select * from user where id=${id}"
        //Instanciar la base de datos de lectura
        val dbreadable = readableDatabase
        //Resultado de la consulta
        val result=dbreadable.rawQuery(scriptConUser,null)
        //Iterable que seguira moviendose hasta hallar el siguiente, cuando no halle acaba
        val userExist = result.moveToFirst()
        //
        val userFound = userdb(0,"","")
        if(userExist){
            do{
                //Especifico la columna mediante el indice
                val id=result.getInt(0)
                val nom=result.getString(1)
                val des=result.getString(2)
                if(id!=null){
                    userFound.id=id
                    userFound.name=nom
                    userFound.desc=des
                }
            }while(result.moveToNext())
        }
        //Cuando tengamos los datos, se debe cerrar el resultado y conexion
        result.close()
        dbreadable.close()
        return userFound
    }

    fun createUserForm(
            parName:String,
            parDesc:String
    ):Boolean{
        //Ahora haremos una conexion de escritura
        val conWriting=writableDatabase
        //Valores a guardar
        val saveData=ContentValues()//INvestigar ContentValues()
        saveData.put("name",parName)
        saveData.put("description",parDesc)

        val resultWriting:Long=conWriting
                .insert(
                        "user",
                        null,
                        saveData
                )

        conWriting.close()
        return if(resultWriting.toInt()==-1)false else true


    }

    fun updateUserForm(
            parName:String,
            parDesc:String,
            parId:Int
    ):Boolean{
        val conWriting=writableDatabase
        val dataUpdate=ContentValues()
        dataUpdate.put("nombre",parName)
        dataUpdate.put("descripcion",parDesc)
        val resultUpdate = conWriting
                .update(
                        "user",//table
                dataUpdate,
                        "id=?",
                        arrayOf(
                                parId.toString()
                        )
                )
        conWriting.close()
        return if(resultUpdate.toInt()==-1) false else true

    }

    fun deleteUser(parId:Int):Boolean{
        val conWriting=writableDatabase
        val resultDelete=conWriting
                .delete(
                        "user",
                        "Id=?",
                        arrayOf(
                                parId.toString()
                        )
                )
        conWriting.close()
        return if(resultDelete.toInt()==-1) false else true
    }
}