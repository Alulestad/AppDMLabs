package com.examenp.recliclerview.data.network.repositories

import com.examenp.recliclerview.data.network.entities.UserDB
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AutenticationRepository {

    private val auth = Firebase.auth
    suspend fun createUsersWithEmailAndPassword(user:String, password:String) =
        runCatching {
            var userdb:UserDB?=null
            val usFirebase=auth.createUserWithEmailAndPassword(
                user,
                password
            ).await().user

            if (usFirebase !=null) {
                userdb = UserDB(usFirebase.uid, usFirebase.email!!, usFirebase.displayName.orEmpty())
            }

            return@runCatching userdb
        }


    suspend fun signInUsers(email:String, password:String):Result<UserDB?> =
        runCatching {
            var userdb:UserDB?=null
            val usFirebase=auth.signInWithEmailAndPassword(email, password).await().user
            if(usFirebase!=null){
                userdb = UserDB(usFirebase.uid, usFirebase.email!!, usFirebase.displayName.orEmpty())

            }
            return@runCatching userdb
        }

}