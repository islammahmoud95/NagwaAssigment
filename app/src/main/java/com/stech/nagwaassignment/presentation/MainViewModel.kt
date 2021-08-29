package com.stech.nagwaassignment.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.stech.data.mapper.AttachedFileMapper
import com.stech.data.usecases.GetAttachedUseCase
import com.stech.domain.common.Result
import com.stech.nagwaassignment.entities.AttachedFiles
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel ( val useCase: GetAttachedUseCase, val mapper: AttachedFileMapper) :ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading
    private val _attachedFile = MutableLiveData<List<AttachedFiles>>()
    val attachedFile = _attachedFile

    fun getAttched() {
        viewModelScope.launch {
            val attachedResult = useCase.invoke()
            _loading.postValue(true)
            when(attachedResult){

                is Result.Success  -> {
                    _loading.postValue(false)

                    _attachedFile.postValue(mapper.toAttachedFile(attachedResult.data))
                }
                is Result.Error -> {
                    _loading.postValue(false)

                }
            }

        }
    }



    class AttachedViewModelFactory(
        private val useCase: GetAttachedUseCase
        , val mapper: AttachedFileMapper
    ) :
        ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(
                useCase,mapper
            ) as T
        }
    }
}