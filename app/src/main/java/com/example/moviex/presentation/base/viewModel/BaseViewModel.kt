package com.example.moviex.presentation.base.viewModel

import android.util.Log
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    init {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} init")
    }

    @CallSuper
    override fun onCleared() {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onCleared")
        super.onCleared()
    }

    companion object {
        private const val LIFECYCLE_TAG = "LifecycleEvent"
    }
}
