package com.example.moviex.presentation.screen.intro

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.example.moviex.R
import com.example.moviex.databinding.FragmentIntroBinding
import com.example.moviex.presentation.base.ui.BaseFragment
import com.example.moviex.util.delegate.RouterDelegate
import com.example.moviex.util.delegate.viewBindings
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroFragment : BaseFragment(R.layout.fragment_intro) {
    private val binding by viewBindings(FragmentIntroBinding::bind)
    private val router by RouterDelegate()
    private val viewModel by viewModel<IntroViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.white)
        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed(
                {
                    router.introToAuth()
                },
                500
        )
    }
}
