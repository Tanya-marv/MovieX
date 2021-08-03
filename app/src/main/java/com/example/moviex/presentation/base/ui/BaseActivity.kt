package com.example.moviex.presentation.base.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseActivity(
        @LayoutRes layoutRes: Int
) : AppCompatActivity(layoutRes) {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onDestroy")
        super.onDestroy()
    }

    fun setStatusBarColorRes(@ColorRes color: Int) {
        setStatusBarColor(ContextCompat.getColor(this, color))
    }

    fun setStatusBarColor(@ColorInt color: Int) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = color
        }
    }

    fun setNavigationBarColor(@ColorRes color: Int) {
        setNavigationBar(ContextCompat.getColor(this, color))
    }

    fun setNavigationBar(@ColorInt color: Int) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            navigationBarColor = color
        }
    }


    companion object {
        private const val LIFECYCLE_TAG = "LifecycleEvent"
    }
}
