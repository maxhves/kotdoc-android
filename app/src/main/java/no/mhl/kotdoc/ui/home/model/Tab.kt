package no.mhl.kotdoc.ui.home.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import no.mhl.kotdoc.R

private const val SECTION_DOCUMENTATION: String = "Documentation"
private const val SECTION_FAVORITES: String = "Favorites"

/**
 * Represents enumeration for 'Tab' types.
 */
enum class Section(
    val title: String,
    @DrawableRes
    val icon: Int
) {
    Documentation(SECTION_DOCUMENTATION, R.drawable.ic_article),
    Favorites(SECTION_FAVORITES, R.drawable.ic_favorite)
}

/**
 * Model representing a whole tab, comprising of;
 * @param section A given tab type
 * @param content The content of the tab as a composable function
 */
class TabContent(
    val section: Section,
    val content: @Composable () -> Unit
)

