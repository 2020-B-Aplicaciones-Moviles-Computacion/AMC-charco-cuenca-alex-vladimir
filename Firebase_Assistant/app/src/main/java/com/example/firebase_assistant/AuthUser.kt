package com.example.firebase_assistant

import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseUser


class AuthUser {

    companion object{
        var user:UserFirebase?;
        init {
            this.user=null;
        }
    }
}