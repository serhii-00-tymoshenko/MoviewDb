package com.serhiitymoshenko.moviewdb.ui.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.moviewdb.data.models.Movie
import com.serhiitymoshenko.moviewdb.data.models.converters.fromDtoResults
import com.serhiitymoshenko.moviewdb.ui.movies.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MoviesHomeViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val popularMovies = MutableLiveData<List<Movie>>(emptyList())
    fun getPopularMovies() = popularMovies

    fun requestPopularMovies(language: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
            val result = async { repository.getPopularMovies(language, page) }
            val isResponseSuccessful = result.await().isSuccessful
            val movies = result.await().body()?.results

            if (isResponseSuccessful) {
                popularMovies.postValue(movies?.fromDtoResults())
            }
        }
}