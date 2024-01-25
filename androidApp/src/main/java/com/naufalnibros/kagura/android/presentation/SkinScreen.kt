package com.naufalnibros.kagura.android.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.naufalnibros.kagura.android.component.SkinDetailSection
import com.naufalnibros.kagura.android.component.SkinListSection
import com.naufalnibros.kagura.domain.models.Skin
import com.naufalnibros.kagura.domain.state.StateSkin
import com.naufalnibros.kagura.viewmodels.SkinViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun SkinScreen(viewModel: SkinViewModel = getViewModel()) {
    val skins by viewModel.skins.collectAsState()
    val selected by viewModel.select.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding(),
    ) {
        val modifier = Modifier.padding(it)
        when (val state = skins) {
            is StateSkin.OnError -> {
                ErrorScreen(modifier) {
                    viewModel.load()
                }
            }
            is StateSkin.OnSuccess -> {
                ContentScreen(
                    modifier,
                    skins = state.data,
                    selected = selected
                ) { item ->
                    viewModel.selected(item)
                }
            }
            else -> LoadingScreen(modifier)
        }
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier, onTryAgain: () -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = ":(",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Black,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = "Cannot proceed your request, please try again!"
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Button(onClick = onTryAgain) {
            Text(text = "Try again")
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Text(
            text = "Loading ...."
        )
    }

}


@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    skins: List<Skin>,
    selected: Skin,
    onSelected: (Skin) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(24.dp)) {
        SkinListSection(
            title = "Mba Kagura Uwu :3",
            subtitle = "Select your favorites kagura skin",
            skins = skins,
        ) {
            onSelected.invoke(it)
        }
        Text(
            text = "Select skin poster to see detail :",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.SemiBold,
        )
        if (selected.id != 0) {
            SkinDetailSection(item = selected)
        }
    }
}

