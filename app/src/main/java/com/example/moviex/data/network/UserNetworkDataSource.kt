package com.example.moviex.data.network

import com.example.moviex.model.User

interface UserNetworkDataSource {
    fun signIn(email: String, password: String)
    fun signUp(email: String, password: String)
    fun logOut()
    fun currentUser(): User?
}