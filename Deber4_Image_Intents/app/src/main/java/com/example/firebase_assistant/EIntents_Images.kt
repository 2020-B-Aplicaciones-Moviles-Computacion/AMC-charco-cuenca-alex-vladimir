package com.example.firebase_assistant

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class EIntents_Images : AppCompatActivity() {

    val RC_TAKE_PICTURE=100;
    val RC_CAMERA=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_intents__images)

        //Botones
        val btnGetImage=findViewById<Button>(R.id.btn_get_image)
        val btnCamera=findViewById<Button>(R.id.btn_camera)

        val imgView=findViewById<ImageView>(R.id.iv_image)

        val intentImg = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intentImg.type="images/*"


        btnCamera.setOnClickListener {
            val intentCam=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intentCam,RC_CAMERA)
        }

        btnGetImage.setOnClickListener {
            val intentImg = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intentImg.setType("image/*")
            startActivityForResult(intentImg,RC_TAKE_PICTURE)
        }

    }


    //Funciones
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val imgView=findViewById<ImageView>(R.id.iv_image)
        when(requestCode){
            100->{
                if(resultCode== RESULT_OK){
                    imgView.setImageURI(data?.data)
                    Toast.makeText(this,"Recovered",LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Not recovered",LENGTH_SHORT).show()
                }
            }
            101->{
                if(resultCode== RESULT_OK){
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgView.setImageBitmap(imageBitmap)
                    Toast.makeText(this,"Image Captured by Camera",LENGTH_SHORT).show()


                }else{
                    Toast.makeText(this,"Camera not Working",LENGTH_SHORT).show()
                }

            }
            else->{
                Toast.makeText(this,"Not Allowed", LENGTH_SHORT).show()
            }

        }
    }






}