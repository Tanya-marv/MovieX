package com.example.moviex.presentation.screen.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviex.data.repository.UserRepository
import com.example.moviex.presentation.base.viewModel.BaseViewModel
import com.example.moviex.presentation.routing.Router

class AuthViewModel(
        private val userRepository: UserRepository
) : BaseViewModel() {

    private val _navigationEventsLiveData = MutableLiveData<(Router) -> Unit>()
    private val _errorLiveData = MutableLiveData<Throwable>()

    val navigationEventsLiveData: LiveData<(Router) -> Unit>
        get() = _navigationEventsLiveData

    val errorLiveData: LiveData<Throwable>
        get() = _errorLiveData

    val isSignedIn: Boolean
        get() = userRepository.currentUser() != null

    fun signUp(email: String, password: String) {
        if (email.isNotEmpty() and password.isNotEmpty()) {
            userRepository.signUp(email, password)
            _navigationEventsLiveData.postValue(Router::authToHome)
        } else {
            _errorLiveData.postValue(Throwable("Cannot sign up..."))
        }
    }

    fun signIn(email: String, password: String) {
        if (email.isNotEmpty() and password.isNotEmpty()) {
            userRepository.signIn(email, password)
            _navigationEventsLiveData.postValue(Router::authToHome)
        } else {
            Log.w("AuthViewModel", "signInWithEmailAndPassword:failure")
            _errorLiveData.postValue(Throwable("Cannot sign up..."))
        }
    }
}
