package com.stech.data.usecases

import com.stech.domain.repositories.AttachedRepository

class GetAttachedUseCase(private val attachedRepository: AttachedRepository) {
    suspend operator fun invoke() = attachedRepository.getAttached()
}