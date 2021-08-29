package com.stech.nagwaassignment.app

import android.app.Application
import com.stech.data.mapper.AttachedFileMapper
import com.stech.data.repositories.AttachedRepositoryImpl
import com.stech.data.usecases.GetAttachedUseCase
import com.stech.nagwaassignment.di.ServiceLocator

class ApplicationApp :Application() {
    val attachedRepository :AttachedRepositoryImpl
    get()= ServiceLocator.provideAttachRepository(this)

    val getAttachedUseCase: GetAttachedUseCase
        get() = GetAttachedUseCase(attachedRepository)
    val attachedFileMapper:AttachedFileMapper=AttachedFileMapper()
}