package com.example.moviex.data.repository

import com.example.moviex.model.User

interface UserRepository {
    fun signIn(email: String, password: String)
    fun signUp(email: String, password: String)
    fun logOut()
    fun currentUser(): User?
}