package no.mhl.kotdoc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import no.mhl.kotdoc.ui.home.Home
import no.mhl.kotdoc.ui.settings.Settings
import no.mhl.kotdoc.ui.settings.detail.SettingDetail
import no.mhl.kotdoc.ui.splash.Splash

/**
 * Destinations used in the [KotDocApp]
 */
object MainDestinations {
    const val SPLASH_ROUTE = "splash"
    const val HOME_ROUTE = "home"
    const val SETTINGS_ROUTE = "settings"
    const val SETTINGS_DETAIL_ROUTE = "setting"
}

/**
 * Models the navigation actions in the app
 */
class MainActions(navController: NavHostController) {
    val splashComplete: () -> Unit = {
        navController.popBackStack()
        navController.navigate(MainDestinations.HOME_ROUTE)
    }
    val openSettings: () -> Unit = {
       navController.navigate(MainDestinations.SETTINGS_ROUTE)
    }
    val selectSetting: (Settings) -> Unit = {
        navController.navigate(MainDestinations.SETTINGS_DETAIL_ROUTE)
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.SPLASH_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController,
        startDestination
    ) {
        // Splash
        composable(MainDestinations.SPLASH_ROUTE) {
            Splash(actions.splashComplete)
        }

        // Home
        composable(MainDestinations.HOME_ROUTE) {
            Home(actions.openSettings)
        }

        // Settings
        composable(MainDestinations.SETTINGS_ROUTE) {
            Settings(actions.selectSetting, actions.upPress)
        }
        composable(MainDestinations.SETTINGS_DETAIL_ROUTE) {
            SettingDetail(actions.upPress)
        }
    }
}