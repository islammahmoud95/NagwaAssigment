package com.stech.domain.repositories

import com.stech.domain.common.Result
import com.stech.domain.entities.Attached
import kotlinx.coroutines.flow.Flow

interface AttachedRepository {


    suspend fun getAttached(): Result<List<Attached>>
}