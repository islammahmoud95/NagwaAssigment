package com.stech.data.mapper


import com.stech.data.api.model.Attatchments
import com.stech.data.api.model.AttatchmentsApiResponse
import com.stech.domain.entities.Attached
import com.stech.nagwaassignment.common.NOTHINGSTATUES
import com.stech.nagwaassignment.entities.AttachedFiles

class AttachedFileMapper {
    fun toAttachedFile(response: List<Attached>): List<AttachedFiles> {
        var attached= ArrayList<AttachedFiles>()
        response.forEach {

            attached.add( AttachedFiles(
                it.id,
                it.name,
                it.type,
                it.url,
                NOTHINGSTATUES
            ))
        }
        return  attached
    }
}