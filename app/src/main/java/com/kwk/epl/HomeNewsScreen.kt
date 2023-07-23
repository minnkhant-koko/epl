package com.kwk.epl

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeNewsScreen() {
    LazyRow(
        modifier = Modifier.padding(top = 12.dp),
        contentPadding = PaddingValues(end = 8.dp)
    ) {
        itemsIndexed(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)) { index, item ->
            HomeNewsTile()
        }
    }
}

@Composable
internal fun HomeNewsTile(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(400.dp)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    LogoImageTop(image = R.drawable.home_team)
                    LogoImageTop(image = R.drawable.away_team)
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextViewAlignEnd(text = "Sun 15, Dec, 2019")
                    TextViewAlignEnd(text = "Old Traffords")
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp)),
                painter = painterResource(id = R.drawable.sample),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Reports",
                style = TextStyle(
                    textAlign = TextAlign.Start
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Manchester United"
                )
                Text(text = "8")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Arsenal")
                Text(text = "2")
            }

            Divider(modifier = Modifier.padding(0.dp, 16.dp), color = Color.Black, thickness = 1.dp)

            Text(
                text = "Get alerts instantly, as stories break across the NFL, NBA, MLB, NHL, NCAA, all world football leagues, and much more. Stay in the know about the latest rumors, news, and predictions across all the leagues you follow.",
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 10.sp
                )
            )

        }
    }
}

@Composable
internal fun TextViewAlignEnd(modifier: Modifier = Modifier, text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = MaterialTheme.typography.subtitle2.copy(
            textAlign = TextAlign.End
        )
    )
}

@Composable
internal fun LogoImageTop(modifier: Modifier = Modifier, image: Int) {
    Image(
        modifier = modifier
            .width(36.dp)
            .height(36.dp),
        painter = painterResource(id = image),
        contentDescription = null
    )
}

@Preview
@Composable
internal fun PreviewHomeNewsTile() {
    MaterialTheme {
        HomeNewsTile()
    }
}