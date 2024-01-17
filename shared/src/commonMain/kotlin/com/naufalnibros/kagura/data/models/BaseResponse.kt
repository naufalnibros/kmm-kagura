package com.naufalnibros.kagura.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    @SerialName("results")
    val data: T
)