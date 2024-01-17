package com.naufalnibros.kagura.android.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.naufalnibros.kagura.domain.models.Skin

@Composable
fun SkinDetailSection(item: Skin) {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (banner, poster, title, info) = createRefs()
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.banner)
                .scale(Scale.FIT)
                .build(),
            modifier = Modifier
                .constrainAs(banner) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(140.dp)
                .background(Color.DarkGray),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.poster)
                .scale(Scale.FIT)
                .build(),
            modifier = Modifier
                .constrainAs(poster) {
                    top.linkTo(banner.bottom)
                    bottom.linkTo(banner.bottom)
                    start.linkTo(parent.start)
                }
                .padding(start = 16.dp, bottom = 16.dp)
                .width(80.dp)
                .height(135.dp)
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = item.title,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 4.dp)
                .constrainAs(title) {
                    top.linkTo(banner.bottom)
                    start.linkTo(poster.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = item.info,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(info) {
                    top.linkTo(title.bottom)
                    start.linkTo(poster.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Left,
        )
    }
}