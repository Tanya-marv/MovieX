package com.example.moviex.presentation.screen.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviex.R
import com.example.moviex.databinding.FragmentHomeBinding
import com.example.moviex.presentation.base.ui.BaseFragment
import com.example.moviex.util.delegate.RouterDelegate
import com.example.moviex.util.delegate.viewBindings
import com.facebook.login.LoginManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()
    private val router by RouterDelegate()
    private val binding by viewBindings(FragmentHomeBinding::bind)

    private val popularMoviesAdapter = HomeAdapter {
        router.homeToDetails(it.id)
    }
    private val topRatedMoviesAdapter = HomeAdapter {
        router.homeToDetails(it.id)
    }
    private val upcomingMoviesAdapter = HomeAdapter {
        router.homeToDetails(it.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.startColorGradient)
        setNavigationBarColor(R.color.endColorGradient)
        setupPopularMoviesRecycler()
        setupTopRatedMoviesRecycler()
        setupUpcomingMoviesRecycler()
        observeLiveData()
        viewModel.retrievePopularMovies()
        viewModel.retrieveTopRatedMovies()
        viewModel.retrieveUpcomingMovies()
        binding.abHome.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mi_favorite -> { true }
                R.id.mi_search -> { true }
                R.id.mi_user -> {
                    viewModel.logOut()
                    Firebase.auth.signOut()
                    LoginManager.getInstance().logOut()
                    router.homeToAuth()
                    true
                }
                else -> true
            }
        }
    }

    private fun setupPopularMoviesRecycler() {
        binding.rvPopularMovies.apply {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupTopRatedMoviesRecycler() {
        binding.rvTopRatedMovies.apply {
            adapter = topRatedMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupUpcomingMoviesRecycler() {
        binding.rvUpcomingMovies.apply {
            adapter = upcomingMoviesAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun observeLiveData() {
        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            val new = movies.map { MoviesItem(it.id, it.posterPath) }
            popularMoviesAdapter.addMovies(new)
        }

        viewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            val new = movies.map { MoviesItem(it.id, it.posterPath) }
            topRatedMoviesAdapter.addMovies(new)
        }

        viewModel.upcomingMoviesLiveData.observe(viewLifecycleOwner) { movies ->
            val new = movies.map { MoviesItem(it.id, it.posterPath) }
            upcomingMoviesAdapter.addMovies(new)
        }
    }
}
