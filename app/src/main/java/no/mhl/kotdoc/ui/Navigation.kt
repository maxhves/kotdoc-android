package no.mhl.kotdoc.ui

import androidx.lifecycle.ViewModel
import no.mhl.kotdoc.ui.ScreenName.DOCUMENTATION
import no.mhl.kotdoc.ui.ScreenName.FAVORITES
import no.mhl.kotdoc.ui.ScreenName.SETTINGS
import no.mhl.kotdoc.ui.ScreenName.SEARCH

// region Screens Declaration
enum class ScreenName { DOCUMENTATION, FAVORITES, SETTINGS, SEARCH }

sealed class Screen(val id: ScreenName) {
    object Documentation : Screen(DOCUMENTATION)
    object Favorites : Screen(FAVORITES)
    object Settings : Screen(SETTINGS)
    object Search : Screen(SEARCH)
}
// endregion

// region Screen To Bundle

// endregion

// region Navigation View Model
class NavigationViewModel() : ViewModel() {

}
// endregion