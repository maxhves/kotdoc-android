package no.mhl.kotdoc.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import no.mhl.kotdoc.ui.MainDestinations.HOME_ROUTE
import no.mhl.kotdoc.ui.MainDestinations.SEARCH_ROUTE
import no.mhl.kotdoc.ui.MainDestinations.SETTINGS_DETAIL_ROUTE
import no.mhl.kotdoc.ui.MainDestinations.SETTINGS_DETAIL_SETTING_KEY
import no.mhl.kotdoc.ui.MainDestinations.SETTINGS_ROUTE
import no.mhl.kotdoc.ui.MainDestinations.SPLASH_ROUTE
import no.mhl.kotdoc.ui.home.Home
import no.mhl.kotdoc.ui.search.Search
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
    const val SETTINGS_DETAIL_SETTING_KEY = "setting"

    const val SEARCH_ROUTE = "search"
}

/**
 * Models the navigation actions in the app
 */
class MainActions(navController: NavHostController) {
    val splashComplete: () -> Unit = {
        navController.popBackStack()
        navController.navigate(HOME_ROUTE)
    }
    val openSettings: () -> Unit = {
       navController.navigate(SETTINGS_ROUTE)
    }
    val selectSetting: (Int) -> Unit = { id: Int ->
        navController.navigate("${SETTINGS_DETAIL_ROUTE}/$id")
    }
    val openSearch: () -> Unit = {
        navController.navigate(SEARCH_ROUTE)
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
        composable(SPLASH_ROUTE) {
            Splash(actions.splashComplete)
        }

        // Home
        composable(HOME_ROUTE) {
            Home(actions.openSettings, actions.openSearch)
        }

        // Settings
        composable(SETTINGS_ROUTE) {
            Settings(actions.selectSetting, actions.upPress)
        }
        composable(
            "${SETTINGS_DETAIL_ROUTE}/{$SETTINGS_DETAIL_SETTING_KEY}",
            arguments = listOf(navArgument(SETTINGS_DETAIL_SETTING_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            SettingDetail(
                arguments.getInt(SETTINGS_DETAIL_SETTING_KEY),
                actions.upPress
            )
        }

        // Search
        composable(SEARCH_ROUTE) {
            Search(actions.upPress)
        }
    }
}