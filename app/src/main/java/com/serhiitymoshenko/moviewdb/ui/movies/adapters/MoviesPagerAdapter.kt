package com.serhiitymoshenko.moviewdb.ui.movies.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.serhiitymoshenko.moviewdb.data.models.Movie
import com.serhiitymoshenko.moviewdb.ui.movies.movie.MovieFragment

class MoviesPagerAdapter(private val fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer<Movie>(this, diffCallback)

    override fun getItemCount() = differ.currentList.size

    override fun createFragment(position: Int): Fragment = MovieFragment.newInstance(differ.currentList[position])

    fun submitList(movies: List<Movie>) = differ.submitList(movies)
}