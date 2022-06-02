package com.example.fadinglightsapp.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fadinglightsapp.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FirebaseRepository {
    private val REPO_DEBUG = "REPO_DEBUG"

    private val storage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun getUsers(): LiveData<List<User>> {
        val cloudResult = MutableLiveData<List<User>>()
        val uid = auth.currentUser?.uid
        db.collection("users")
            .limit(10)
            .get()
            .addOnSuccessListener {
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
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
    }

    fun updateTime(time: Double) {
        db.runTransaction {
            val snapshot = it.get(db.collection("users").document(auth.currentUser?.uid!!))
            val u = snapshot.toObject(User::class.java)
            if (u?.time == null || u.time!! > time) {
                it.update(
                    db.collection("users")
                        .document(auth.currentUser?.uid!!), "time", time
                )
            }
        }.addOnSuccessListener {
            Log.d(REPO_DEBUG, "Time successfully written!")
        }.addOnFailureListener {
            Log.d(REPO_DEBUG, it.message.toString())
        }
    }
}