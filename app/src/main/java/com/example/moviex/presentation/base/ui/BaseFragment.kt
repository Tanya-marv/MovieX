package com.example.moviex.presentation.base.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
        @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected val baseActivity: BaseActivity
        get() = requireActivity() as BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onViewCreated")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onDestroy")
        super.onDestroy()
    }

    override fun onDestroyView() {
        Log.d(LIFECYCLE_TAG, "${javaClass.simpleName} onDestroyView")
        super.onDestroyView()
    }

    fun setStatusBarColor(@ColorRes color: Int) {
        baseActivity.setStatusBarColorRes(color)
    }

    fun setNavigationBarColor(@ColorRes color: Int) {
        baseActivity.setNavigationBarColor(color)
    }

    companion object {
        private const val LIFECYCLE_TAG = "LifecycleEvent"
    }
}
