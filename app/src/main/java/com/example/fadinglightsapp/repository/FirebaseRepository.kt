package com.example.fadinglightsapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fadinglightsapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun getUsers(): LiveData<List<User>> {
        val cloudResult = MutableLiveData<List<User>>()
        db.collection("users")
            .limit(10)
            .get()
            .addOnSuccessListener { it ->
                val users = it.documents.map { it.toObject(User::class.java)!! }
                cloudResult.postValue(users)
            }

        return cloudResult
    }

    fun saveUser(user: User) {
        val uid = auth.currentUser?.uid
        db.collection("users")
            .document(uid!!)
            .set(user)
            .addOnSuccessListener { Log.d("SAVE_USER", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.e("SAVE_USER", "Error writing document", e) }
    }

    fun updateTime(time: Double) {
        db.runTransaction {
            val snapshot = it.get(db.collection("users").document(auth.currentUser?.uid!!))
            val u = snapshot.toObject(User::class.java)
            if (u?.time == null || u.time > time) {
                it.update(
                    db.collection("users")
                        .document(auth.currentUser?.uid!!),
                    "time", time
                )
            }
        }.addOnSuccessListener {
            Log.d("UPDATE_TIME", "Time successfully written!")
        }.addOnFailureListener {
            Log.e("UPDATE_TIME", it.message.toString())
        }
    }
}
