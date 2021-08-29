package com.stech.data.repositories

import com.stech.data.api.APIEndPoint
import com.stech.data.api.model.Attatchments
import com.stech.data.mapper.AttachedApiResponseMapper
import com.stech.domain.entities.Attached
import com.stech.domain.repositories.AttachedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.stech.domain.common.Result
import retrofit2.Response

class AttachedRepositoryImpl (
    private val service: APIEndPoint,
    private val mapper: AttachedApiResponseMapper

        ): AttachedRepository {

    override suspend fun getAttached(): Result<List<Attached>> =
        withContext(Dispatchers.IO) {

            try {

                val response = service.getAttached()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toAttached(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}