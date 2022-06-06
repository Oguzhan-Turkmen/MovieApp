package com.oguzhanturkmen.movieapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.oguzhanturkmen.movieapp.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Movie):Long

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}