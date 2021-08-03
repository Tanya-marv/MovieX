package com.example.moviex.util.delegate

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.moviex.presentation.routing.Router
import com.example.moviex.presentation.routing.impl.RouterImpl
import kotlin.reflect.KProperty

class RouterDelegate {

    lateinit var router: Router

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): Router {
        return if (::router.isInitialized) router else thisRef.findRouter().also { router = it }
    }
}

fun Fragment.findRouter(): Router {
    return RouterImpl(findNavController())
}
