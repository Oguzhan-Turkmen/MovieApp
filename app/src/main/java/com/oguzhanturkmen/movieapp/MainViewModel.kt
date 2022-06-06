package com.oguzhanturkmen.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.movieapp.model.Movie
import com.oguzhanturkmen.movieapp.model.Movies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetrofitRepository) : ViewModel() {
    val popularMoviesList: MutableLiveData<Movies> = MutableLiveData()
    val searchMoviesList: MutableLiveData<Movies> = MutableLiveData()
    val savedlivedata = MutableLiveData<List<Movie>>()
    init {
        getSavedMovies()
    }

    fun getObserverLiveData(): MutableLiveData<Movies> {
        return popularMoviesList
    }

    fun getObserverSearvhLiveData(): MutableLiveData<Movies> {
        return searchMoviesList
    }

    fun loadPopularData(page: String) = viewModelScope.launch {
        repository.getPopularMovies(page, popularMoviesList)
    }

    fun loadSearchData(page: String, searchQuery: String) {
        repository.searchMovies(page, searchQuery, searchMoviesList)
    }

    fun saveMovie(movie: Movie) = viewModelScope.launch {
        repository.upsert(movie)
    }

    fun getSavedMovies() {
        viewModelScope.launch {
            savedlivedata.postValue(repository.getSavedMovies())
        }
    }

    fun deleteMovies(movie: Movie) = viewModelScope.launch {
        repository.deleteMovies(movie)
        getSavedMovies()
    }
}