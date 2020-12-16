package com.example.a02_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button

class CIntentRespuesta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_intent_respuesta)

        val btnSelectContact = findViewById<Button>(R.id.bt_get_contact)
        btnSelectContact.setOnClickListener{
            val intentResp = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intentResp,304)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            304->{
                if(resultCode== RESULT_OK){
                    Log.i("resultado","Usuario selecciono un contacto")

                    val uri=data?.data
                    if(uri!=null){
                        val cursor = contentResolver.query(
                            uri,
                            null,
                            null,
                            null,
                            null,
                            null
                        )
                        cursor?.moveToFirst()
                        val indexPhone = cursor?.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                        val phone = cursor?.getString(
                            indexPhone!!
                        )
                        cursor?.close()
                        Log.i("resultado","Phone: ${phone}")
                    }

                }else{
                    Log.i("resultado","Usuario no selecciono un contacto")
                }
            }

        }
    }


}