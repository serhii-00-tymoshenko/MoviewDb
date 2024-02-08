package com.serhiitymoshenko.moviewdb.ui.movies

import android.graphics.ColorSpace.Adaptation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.serhiitymoshenko.moviewdb.databinding.FragmentMoviesHomeBinding
import com.serhiitymoshenko.moviewdb.ui.movies.adapters.MoviesPagerAdapter
import com.serhiitymoshenko.moviewdb.ui.movies.viewmodel.MoviesHomeViewModel
import org.koin.android.ext.android.inject

class MoviesHomeFragment : Fragment() {

    private var _binding: FragmentMoviesHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by inject<MoviesHomeViewModel>()

    private lateinit var moviesPagerAdapter: MoviesPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setupPager(activity)
        getPopularMovies()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getPopularMovies().observe(viewLifecycleOwner) { movies ->
            moviesPagerAdapter.submitList(movies)
        }
    }

    private fun getPopularMovies() {
        viewModel.requestPopularMovies("uk-UA", 1)
    }

    private fun setupPager(activity: FragmentActivity) {
        moviesPagerAdapter = MoviesPagerAdapter(activity)
        binding.moviesPager.apply {
            adapter = moviesPagerAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}