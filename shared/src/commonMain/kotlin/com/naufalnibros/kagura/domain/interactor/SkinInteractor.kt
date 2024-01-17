package com.naufalnibros.kagura.domain.interactor

import com.naufalnibros.kagura.domain.models.Skin

interface SkinInteractor {
    suspend fun getSkins(): List<Skin>
}