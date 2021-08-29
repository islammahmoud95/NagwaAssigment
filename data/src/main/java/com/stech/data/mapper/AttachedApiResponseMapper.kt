package com.stech.data.mapper


import com.stech.data.api.model.Attatchments
import com.stech.data.api.model.AttatchmentsApiResponse
import com.stech.domain.entities.Attached
import retrofit2.Response

class AttachedApiResponseMapper {
    fun toAttached(response: List<Attatchments>): List<Attached> {
        var attached= ArrayList<Attached>()
        response.forEach {

            attached.add( Attached(
                it.id,
                it.name,
                it.type,
                it.url
            ))
        }
        return  attached
    }
}