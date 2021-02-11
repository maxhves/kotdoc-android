package no.mhl.kotdoc.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import no.mhl.kotdoc.ui.ScreenName.SPLASH
import no.mhl.kotdoc.ui.ScreenName.DOCUMENTATION
import no.mhl.kotdoc.ui.ScreenName.FAVORITES
import no.mhl.kotdoc.ui.ScreenName.SETTINGS
import no.mhl.kotdoc.ui.ScreenName.SEARCH
import no.mhl.kotdoc.utils.getMutableStateOf
import no.mhl.kotdoc.ui.Screen.Splash
import no.mhl.kotdoc.ui.Screen.Documentation
import no.mhl.kotdoc.ui.Screen.Favorites
import no.mhl.kotdoc.ui.Screen.Settings
import no.mhl.kotdoc.ui.Screen.Search

// region Screens Declaration
enum class ScreenName { SPLASH, DOCUMENTATION, FAVORITES, SETTINGS, SEARCH }

sealed class Screen(val id: ScreenName) {
    object Splash : Screen(SPLASH)
    object Documentation : Screen(DOCUMENTATION)
    object Favorites : Screen(FAVORITES)
    object Settings : Screen(SETTINGS)
    object Search : Screen(SEARCH)
}
// endregion

// region Screen To Bundle
private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"
private const val SIS_POST = "post"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name)
}

private fun Bundle.toScreen() = when (ScreenName.valueOf(getStringOrThrow(SIS_NAME))) {
    SPLASH -> Splash
    DOCUMENTATION -> Documentation
    FAVORITES -> Favorites
    SETTINGS -> Settings
    SEARCH -> Search
}

private fun Bundle.getStringOrThrow(key: String) =
    requireNotNull(getString(key)) { "Missing key '$key' in $this" }
// endregion

// region Navigation View Model
class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Splash,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Documentation
        currentScreen = Documentation
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }

}
// endregion