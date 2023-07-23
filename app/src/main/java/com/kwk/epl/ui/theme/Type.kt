package com.kwk.epl.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kwk.epl.R

// Set of Material typography styles to start with
val Inter = FontFamily(
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_regular),
    Font(R.font.inter_light, FontWeight.Light)
)
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    body2 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 8.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)