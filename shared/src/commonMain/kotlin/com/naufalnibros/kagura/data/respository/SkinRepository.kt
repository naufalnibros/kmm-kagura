package com.naufalnibros.kagura.data.respository

import com.naufalnibros.kagura.data.mapper.SkinMapper
import com.naufalnibros.kagura.data.source.SkinRemote
import com.naufalnibros.kagura.domain.models.Skin

class SkinRepository(
    private val remote: SkinRemote,
    private val mapper: SkinMapper
) {
    suspend fun getSkins() : List<Skin> = remote.getSkins().data.map {
        mapper.mapSkinItem(it)
    }
}