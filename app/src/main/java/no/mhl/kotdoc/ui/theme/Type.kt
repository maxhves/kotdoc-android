package no.mhl.kotdoc.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.R

/**
 * Manrope default font family
 */
val Manrope = FontFamily(
    Font(R.font.manrope_extralight, FontWeight.ExtraLight),
    Font(R.font.manrope_light, FontWeight.Light),
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_semibold, FontWeight.SemiBold),
    Font(R.font.manrope_bold, FontWeight.Bold),
    Font(R.font.manrope_extrabold, FontWeight.ExtraBold)
)

val typography = Typography(defaultFontFamily = Manrope)

// region Markdown Typography
val MarkdownH1 = TextStyle(
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    fontFamily = Manrope
)

val MarkdownH2 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    fontFamily = Manrope
)

val MarkdownH3 = TextStyle(
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    fontFamily = Manrope
)

val MarkdownBody1 = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    fontFamily = Manrope
)

val MarkdownCode = TextStyle(
    fontWeight = FontWeight.Normal,
    fontFamily = FontFamily.Monospace,
    fontSize = 14.sp
)
// endregion