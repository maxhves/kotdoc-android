package no.mhl.kotdoc.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.R

/**
 * Manrope default font family
 */
val Manrope = fontFamily(
    font(R.font.manrope_extralight, FontWeight.ExtraLight),
    font(R.font.manrope_light, FontWeight.Light),
    font(R.font.manrope_regular, FontWeight.Normal),
    font(R.font.manrope_medium, FontWeight.Medium),
    font(R.font.manrope_semibold, FontWeight.SemiBold),
    font(R.font.manrope_bold, FontWeight.Bold),
    font(R.font.manrope_extrabold, FontWeight.ExtraBold)
)

val typography = Typography(
    defaultFontFamily = Manrope,

    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
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