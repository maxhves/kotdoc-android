package no.mhl.kotdoc.ui.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import no.mhl.kotdoc.R

/**
 * Enumeration representing the actions we have in the Home Screen.
 */
enum class Action(
    @StringRes
    val description: Int,
    @DrawableRes
    val icon: Int
) {
    Search(R.string.action_search, R.drawable.ic_search),
    Settings(R.string.action_settings, R.drawable.ic_settings)
}

/**
 * Model representing an AppBarLayout action item, comprising of a;
 * @param action the action item we want to use
 * @param onClick the onClick action as a function
 */
class HomeMenuItem(
    val action: Action,
    val onClick: () -> Unit
)