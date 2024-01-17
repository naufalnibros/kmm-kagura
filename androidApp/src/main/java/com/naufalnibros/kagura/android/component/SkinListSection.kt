package com.naufalnibros.kagura.android.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.naufalnibros.kagura.domain.models.Skin

@Composable
fun SkinListSection(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    skins: List<Skin>,
    onItemClicked: (Skin) -> Unit
) {
    val gridState = rememberLazyListState()
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
        )
        if (subtitle.isEmpty().not()) {
            Text(
                text = subtitle, modifier = Modifier
                    .padding(start = 16.dp), color = MaterialTheme.colors.onSurface.copy(0.5f),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Left
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            state = gridState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            itemsIndexed(skins) { _, item ->
                Log.d(javaClass.simpleName, "SkinListSection: $item")
                SkinPosterSection(
                    url = item.poster,
                    title = item.title,
                    modifier = Modifier.clickable {
                        onItemClicked.invoke(item)
                    }
                )
            }
        }
    }
}