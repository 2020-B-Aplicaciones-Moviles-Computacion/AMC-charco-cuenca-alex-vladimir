package com.example.firebase_assistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class E_Fragment : AppCompatActivity() {

    lateinit var curFragment:Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_e__fragment)


        val btnFrag1=findViewById<Button>(R.id.btn_f1)
        btnFrag1.setOnClickListener {
            createFrag1()
        }
        val btnFrag2=findViewById<Button>(R.id.btn_f2)
        btnFrag2.setOnClickListener {
            createFrag2()
        }

    }

    fun createFrag1(){
        //Manager
        val fragManager=supportFragmentManager
        //Transactions
        val fragTransaction=fragManager.beginTransaction()
        //Create fragment instance
        val fragm1=Fragment1()
        val arguments=Bundle()
        arguments.putString("name","Manaseses")
        arguments.putInt("age",4)
        fragm1.arguments=arguments

        //Anadir el fragmento->Remplazar el frag actual con el que tenems
        //a traves del relative layout
        fragTransaction.replace(R.id.rl_fragment,fragm1)
        curFragment=fragm1
        fragTransaction.commit()
    }

    fun createFrag2(){
        //Manager
        val fragManager=supportFragmentManager
        //Transactions
        val fragTransaction=fragManager.beginTransaction()
        //Create fragment instance
        val fragm2=Fragment2()

        //Anadir el fragmento->Remplazar el frag actual con el que tenems
        //a traves del relative layout
        fragTransaction.replace(R.id.rl_fragment,fragm2)
        curFragment=fragm2
        fragTransaction.commit()
    }
}