package com.oguzhanturkmen.movieapp.di


import android.content.Context
import com.oguzhanturkmen.movieapp.BASE_URL
import com.oguzhanturkmen.movieapp.API.RetrofitServiceIntance
import com.oguzhanturkmen.movieapp.database.MovieDao
import com.oguzhanturkmen.movieapp.database.MovieDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule (){

    @Singleton
    @Provides
    fun getMovieDao(movieDatabase: MovieDatabase):MovieDao{
        return movieDatabase.getMovieDao()
    }
    @Singleton
    @Provides
    fun getRoomDbInstance(@ApplicationContext appContext: Context):MovieDatabase{
        return MovieDatabase.createDatabase(appContext)
    }
    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): RetrofitServiceIntance {
        return retrofit.create(RetrofitServiceIntance::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

