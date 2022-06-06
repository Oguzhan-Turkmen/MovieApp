package com.oguzhanturkmen.movieapp

import com.oguzhanturkmen.movieapp.model.Movies
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServiceIntance {
    //?api_key=146006eead10e81276d447ee8c360b4e
    @GET("3/movie/popular")
    fun getPopularMovies( @Query("page") query: String,@Query("api_key") api_Key:String= API_KEY): Call<Movies>

    //?api_key=146006eead10e81276d447ee8c360b4e
    @GET("3/search/movie")
    fun searchMovies(@Query("query") searchQuery:String, @Query("page") pageNumber:String,@Query("api_key") api_Key:String= API_KEY) : Call<Movies>
}