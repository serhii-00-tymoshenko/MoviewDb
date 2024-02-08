package com.serhiitymoshenko.moviewdb.ui.movies.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.serhiitymoshenko.moviewdb.data.models.Movie
import com.serhiitymoshenko.moviewdb.databinding.FragmentMovieBinding

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movie by lazy {
        arguments?.getParcelable(MOVIE_ARGUMENT_KEY) ?: Movie(
            0,
            "Not found",
            0.0,
            "Not found",
            "Not found"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setContent()
    }

    private fun setContent() {
        binding.apply {
            imagePoster.load(movie.posterPath)
            textTitle.text = movie.originalTitle
            textRating.text = movie.popularity.toString()
            textReleaseDate.text = movie.releaseDate
        }
    }

    companion object {
        private const val MOVIE_ARGUMENT_KEY = "movie_argument_key"

        @JvmStatic
        fun newInstance(movie: Movie) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE_ARGUMENT_KEY, movie)
                }
            }
    }
}