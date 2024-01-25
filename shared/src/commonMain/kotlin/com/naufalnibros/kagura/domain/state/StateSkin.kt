package com.naufalnibros.kagura.domain.state

import com.naufalnibros.kagura.domain.models.Skin

sealed interface StateSkin {
    object OnInit: StateSkin
    object OnLoading: StateSkin
    data class OnSuccess(val data: List<Skin>) : StateSkin
    data class OnError(val message: String) : StateSkin
}