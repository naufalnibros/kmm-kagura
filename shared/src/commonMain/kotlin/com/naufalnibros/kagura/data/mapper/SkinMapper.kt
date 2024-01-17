package com.naufalnibros.kagura.data.mapper

import com.naufalnibros.kagura.data.models.SkinResponse
import com.naufalnibros.kagura.domain.models.Skin

class SkinMapper {

    fun mapSkinItem(response: SkinResponse) = Skin(
        id = response.id,
        title = response.title,
        info = response.info,
        banner = response.bannerUrl ?: "https://static.wikia.nocookie.net/mobile-legends/images/f/ff/Kagura_%28Onmyouji_Master%29.jpg/revision/latest/scale-to-width-down/1000?cb=20211212135733",
        poster = response.posterUrl ?: "https://static.wikia.nocookie.net/mobile-legends/images/8/8e/Hero251-portrait.png/revision/latest?cb=20220525164837"
    )

}