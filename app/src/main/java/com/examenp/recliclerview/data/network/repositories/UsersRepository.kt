package com.examenp.recliclerview.data.network.repositories

import com.examenp.recliclerview.data.network.entities.UserDB
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UsersRepository {

    private val db= Firebase.firestore

    suspend fun saveUserDB(id:String, email:String, name: String):Result<UserDB> = runCatching{
        val us= UserDB(id,email,name)
        db.collection("Users").add(us).await() //el json se va guardar en una coleccion
            //el await es para que espera a que termine

        return@runCatching us
    }

    suspend fun getUserByID(id:String)=runCatching {
        val us = UserDB(id, "", "")

        val v= db.collection("Users")
            .document(us.id)
            .get()
            .await<DocumentSnapshot?>()?.toObject<UserDB>(UserDB::class.java) //se encarga de transofrmar a UserDB

        return@runCatching v

    }//.getOrNull()

    suspend fun updateUserByID(id:String)=runCatching {
        val us = UserDB(id, "", "")

        val v=db.collection("Users")
                    .document(us.id)
                    .get()
                    .await<DocumentSnapshot?>()?.toObject<UserDB>(UserDB::class.java) //se encarga de transofrmar a UserDB

        //PARA ACTUALIZAR PISTA:
        if(v!=null){
            v!!.email="dddd"
            db.collection("Users").document(v!!.id).set(v)

        }


    }

}