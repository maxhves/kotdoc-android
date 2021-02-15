package no.mhl.kotdoc.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import no.mhl.kotdoc.data.AppContainer
import no.mhl.kotdoc.ui.theme.KotDocTheme
import no.mhl.kotdoc.ui.Screen.Splash
import no.mhl.kotdoc.ui.Screen.Home
import no.mhl.kotdoc.ui.Screen.Documentation
import no.mhl.kotdoc.ui.Screen.Search
import no.mhl.kotdoc.ui.Screen.Settings
import no.mhl.kotdoc.ui.Screen.Favorites
import no.mhl.kotdoc.ui.documentation.DocumentationScreen
import no.mhl.kotdoc.ui.favorites.FavoritesScreen
import no.mhl.kotdoc.ui.home.HomeScreen
import no.mhl.kotdoc.ui.settings.SettingsScreen
import no.mhl.kotdoc.ui.splash.SplashScreen

// region App Entry
@Composable
fun KotDocApp(container: AppContainer, navigationViewModel: NavigationViewModel) {
    KotDocTheme {
        AppContent(navigationViewModel)
    }
}
// endregion

@Composable
private fun AppContent(navigationViewModel: NavigationViewModel) {
    Crossfade(navigationViewModel.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Splash -> SplashScreen(
                    navigateTo = navigationViewModel::navigateTo
                )
                is Home -> HomeScreen(
                    navigateTo = navigationViewModel::navigateTo
                )
                is Documentation -> DocumentationScreen()
                is Favorites -> FavoritesScreen()
                is Search -> {}
                is Settings -> SettingsScreen(
                    navigateTo = navigationViewModel::navigateTo,
                    onBack = { navigationViewModel.onBack() }
                )
            }
        }
    }
}
