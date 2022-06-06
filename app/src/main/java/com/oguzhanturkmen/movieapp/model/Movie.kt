package com.oguzhanturkmen.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(
    tableName = "movies"
)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
):Serializable