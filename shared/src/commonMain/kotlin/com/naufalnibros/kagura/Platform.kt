package com.naufalnibros.kagura

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform