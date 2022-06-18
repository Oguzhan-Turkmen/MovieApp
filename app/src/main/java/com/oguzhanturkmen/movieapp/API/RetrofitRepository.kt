package com.oguzhanturkmen.movieapp.API

import androidx.lifecycle.MutableLiveData
import com.oguzhanturkmen.movieapp.database.MovieDatabase
import com.oguzhanturkmen.movieapp.model.Movie
import com.oguzhanturkmen.movieapp.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private  val retrofitServiceInstance: RetrofitServiceIntance, val db: MovieDatabase) {

    fun getPopularMovies(page:String,liveData: MutableLiveData<Movies>){
        retrofitServiceInstance.getPopularMovies(page).enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun searchMovies(pageNumber: String, searchQuery: String, liveData: MutableLiveData<Movies>){
        retrofitServiceInstance.searchMovies(searchQuery,pageNumber).enqueue(object :Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    suspend fun upsert(movie: Movie) = db.getMovieDao().upsert(movie)

    suspend fun getSavedMovies():List<Movie>{
        val list =db.getMovieDao().getAllMovies()
        return list
    }

    suspend fun deleteMovies(movie: Movie)= db.getMovieDao().deleteMovie(movie)
}