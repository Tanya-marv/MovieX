package com.example.moviex.data.repository.impl

import com.example.moviex.data.network.UserNetworkDataSource
import com.example.moviex.data.repository.UserRepository
import com.example.moviex.model.User

class UserRepositoryImpl(
    private val firebase: UserNetworkDataSource
) : UserRepository{
    override fun signIn(email: String, password: String) = firebase.signIn(email, password)

    override fun signUp(email: String, password: String) = firebase.signUp(email, password)

    override fun logOut() = firebase.logOut()

    override fun currentUser(): User? = firebase.currentUser()
}