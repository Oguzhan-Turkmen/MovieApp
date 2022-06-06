package com.oguzhanturkmen.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oguzhanturkmen.movieapp.model.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract  class MovieDatabase:RoomDatabase() {
    abstract fun getMovieDao():MovieDao

    companion object{

        @Volatile
        private var instance : MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance= it}
        }
        fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_db.db"
            ).build()
        }
    }

