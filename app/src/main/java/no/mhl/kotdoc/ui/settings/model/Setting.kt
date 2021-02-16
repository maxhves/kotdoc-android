package no.mhl.kotdoc.ui.settings.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class Setting(
    @StringRes
    val label: Int,
    @DrawableRes
    val icon: Int,
    val iconTint: Color,
    val onClick: () -> Unit
)