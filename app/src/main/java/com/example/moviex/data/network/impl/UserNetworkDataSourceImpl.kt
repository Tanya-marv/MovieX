package com.example.moviex.data.network.impl

import android.util.Log
import com.example.moviex.data.network.UserNetworkDataSource
import com.example.moviex.data.network.mapper.ApiModelMapper
import com.example.moviex.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserNetworkDataSourceImpl(
        private val userMapper: ApiModelMapper
) : UserNetworkDataSource {

    private val auth = Firebase.auth

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("AuthModel", "signInWithEmailAndPassword:success")
                    } else {
                        Log.w("AuthModel", "signInWithEmailAndPassword:failure", task.exception)
                    }
                }
    }

    override fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("AuthViewModel", "createUserWithEmail:success")
                    }
                }
    }

    override fun logOut() {
        auth.signOut()
    }

    override fun currentUser(): User? {
        return if (auth.currentUser != null) {
            userMapper.map(auth.currentUser)
        } else null
    }
}