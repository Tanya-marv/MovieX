package com.example.moviex.presentation.screen.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviex.R
import com.example.moviex.databinding.FragmentDetailBinding
import com.example.moviex.presentation.base.ui.BaseFragment
import com.example.moviex.util.delegate.RouterDelegate
import com.example.moviex.util.delegate.viewBindings
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment(R.layout.fragment_detail) {

    private val viewModel by viewModel<DetailsViewModel>()
    private val router by RouterDelegate()
    private val binding by viewBindings(FragmentDetailBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    private val genreMovieAdapter = DetailsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarColor(R.color.startColorGradient)
        setNavigationBarColor(R.color.endColorGradient)
        setupGenresMovieRecycler()
        viewModel.retrieveMovieDetails(args.idMovie)
        observeLiveData()
    }

    private fun setupGenresMovieRecycler() {
        binding.rvGenre.apply {
            adapter = genreMovieAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun observeLiveData() {
        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) {
            binding.apply {
                tvTitle.text = it.title
                ivBackdrop.load("https://image.tmdb.org/t/p/w1280${it.backdropPath}")
                ivPoster.load("https://image.tmdb.org/t/p/w342${it.posterPath}")
                rbMovies.rating = it.voteAverage / 2
                tvData.text = it.releaseDate
                tvOverview.text = it.overview
            }
        }
        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner) { movie ->
            val new = movie.genres.map { Genre(it.id, it.name) }
            genreMovieAdapter.addGenre(new)
        }
    }
}
