package com.naufalnibros.kagura.domain.interactor

import com.naufalnibros.kagura.data.respository.SkinRepository
import com.naufalnibros.kagura.domain.models.Skin

class SkinInteractorImpl(private val repository: SkinRepository): SkinInteractor {
    override suspend fun getSkins(): List<Skin> {
        return repository.getSkins()
    }
}