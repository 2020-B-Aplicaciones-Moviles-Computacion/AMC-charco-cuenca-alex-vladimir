package com.example.a02_android

class  IPostHttp(
    val id:Int,
    var userId:Any,
    val title:String,
    val body:String
) {
    init{

        if(userId is String){
            userId=(userId as String).toInt()
        }
        if(userId is Int){
            userId=userId
        }
    }



}