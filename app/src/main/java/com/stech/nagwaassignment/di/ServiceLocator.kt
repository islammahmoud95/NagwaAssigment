package com.stech.nagwaassignment.di

import android.content.Context
import com.stech.data.api.NetworkModule
import com.stech.data.mapper.AttachedApiResponseMapper
import com.stech.data.repositories.AttachedRepositoryImpl
import com.stech.domain.repositories.AttachedRepository

object ServiceLocator {

    private val networkModule by lazy {
        NetworkModule()
    }
    @Volatile
    var attachRepository: AttachedRepository? = null

    fun provideAttachRepository(context: Context): AttachedRepositoryImpl {
        // useful because this method can be accessed by multiple threads
        synchronized(this) {
            return (attachRepository ?: createBooksRepository(context)) as AttachedRepositoryImpl
        }
    }

    private fun createBooksRepository(context: Context): AttachedRepositoryImpl {
        val newRepo =
            AttachedRepositoryImpl(
                    networkModule.createBooksApi("https://nagwa.free.beeceptor.com/movies/"),
                AttachedApiResponseMapper()
            )
        attachRepository = newRepo
        return newRepo
    }

}