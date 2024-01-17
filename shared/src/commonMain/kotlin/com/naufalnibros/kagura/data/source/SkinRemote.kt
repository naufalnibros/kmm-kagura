package com.naufalnibros.kagura.data.source

import com.naufalnibros.kagura.data.models.BaseResponse
import com.naufalnibros.kagura.data.models.SkinResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class SkinRemote(private val httpClient: HttpClient) {
    suspend fun getSkins(): BaseResponse<List<SkinResponse>> {
        return httpClient.get("https://naufalnibros.github.io/kagura_skins.json").body()
    }
}