/*package com.example.firebase_assistant

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.inputmethodservice.Keyboard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class EIntent_Image : AppCompatActivity() {
    val RC_TAKE_PICTURE=100;
    val RC_CAMERA=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e_intent__image)

        val btnGetImage=findViewById<Button>(R.id.btn_img_file)
        val btnCamera=findViewById<Button>(R.id.btn_img_camera)
        val btnUplFile1=findViewById<Button>(R.id.btn_upload_img)
        val btnDownload=findViewById<Button>(R.id.btn_img_download)

        val imgView=findViewById<ImageView>(R.id.iv_img)

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

        btnUplFile1.setOnClickListener {
            uploadFile()
        }


        btnDownload.setOnClickListener {
            Log.i("fb-storage","Downloading")
            downloadImage()
        }

    }



    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        val imgView = findViewById<ImageView>(R.id.iv_img)
        when (requestCode) {
            100 -> {
                if (resultCode == RESULT_OK) {
                    imgView.setImageURI(data?.data)
                    Toast.makeText(this, "${"Recovered"}", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this, "Not recovered", Toast.LENGTH_SHORT).show()
                }
            }
            101->{
                if (resultCode == RESULT_OK) {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgView.setImageBitmap(imageBitmap)
                    Toast.makeText(this, "Image Captured by Camera", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Camera not Working", Toast.LENGTH_SHORT).show()
                }
            }
            else->{
                Toast.makeText(this, "Not Allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun uploadFile() {

        val imgView = findViewById<ImageView>(R.id.iv_img)
        if(imgView!=null){
            Log.i("fb-storage", "Uploading")

            val storage = Firebase.storage("gs://moviles-charco-alex.appspot.com")
            val storageRef = storage.reference

            //Nombre con que se guarda

            val uploadRef = storageRef.child("images/1.jpg")

            // Get the data from an ImageView as bytes
            imgView.isDrawingCacheEnabled = true
            imgView.buildDrawingCache()

            val bitmap = (imgView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            uploadRef.putBytes(data)
                .addOnFailureListener {
                    Toast.makeText(this,"Failure", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    Toast.makeText(this,"Sucess", Toast.LENGTH_SHORT).show()
                }
        }else{            Toast.makeText(this,"Image View Empty", Toast.LENGTH_SHORT).show() }
    }

    fun downloadImage(){
        val etFile=findViewById<EditText>(R.id.et_img_uri)

        val storage = Firebase.storage
        val storageRef = storage.reference

        val gsReference = storage.getReferenceFromUrl("gs://moviles-charco-alex.appspot.com/images/"+etFile.text)

        var islandRef = storageRef.child("images/island.jpg")

        val ONE_MEGABYTE: Long =  1000000000
        gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            etFile.setText("")


            val imgView = findViewById<ImageView>(R.id.iv_img)

            val  bmp:Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size);
           // image.setImageBitmap(Bitmap.createScaledBitmap(bmp, image.getWidth(), image.getHeight(), false));
             imgView.setImageBitmap(bmp)

            Toast.makeText(this,"Sucessful Download", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {

            Toast.makeText(this,"Failed Download", Toast.LENGTH_SHORT).show()
            Log.i("fb-storage","${it}")
        }

    }
}*/