package com.kwk.epl

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun ChooseTeamScreen() {
    Box(modifier = Modifier.background(color = Color.Gray)) {
        LazyVerticalGrid(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(21) {
                    ChooseTeamTile()
                }
            }
        )
    }
}

@Preview
@Composable
internal fun ChooseTeamTile() {
    Box(
        modifier = Modifier
            .background(color = Color.White, shape = CircleShape)
            .aspectRatio(1f),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.home_team),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
            contentDescription = null
        )
    }
}

@Preview(device = "id:pixel_5", showSystemUi = true)
@Composable
internal fun PreviewChooseTeamScreen() {
    MaterialTheme {
        ChooseTeamScreen()
    }
}