package com.example.moviex.presentation.routing

interface Router {
    fun back()

    fun navigate(navigator: (Router) -> Unit)

    fun authToHome()

    fun homeToAuth()

    fun introToAuth()

    fun homeToDetails(id: Long)
}
