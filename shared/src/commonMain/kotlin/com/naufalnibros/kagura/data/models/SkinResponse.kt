package com.naufalnibros.kagura.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkinResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("info")
    val info: String = "",

    @SerialName("bannerUrl")
    val bannerUrl: String? = null,

    @SerialName("posterUrl")
    val posterUrl: String? = null
)
