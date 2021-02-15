package no.mhl.kotdoc.ui.documentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen
import no.mhl.kotdoc.ui.theme.black
import no.mhl.kotdoc.ui.theme.blackTransparent
import no.mhl.kotdoc.ui.documentation.Action.Search
import no.mhl.kotdoc.ui.documentation.Action.Settings
import no.mhl.kotdoc.ui.settings.SettingsScreen

// region Tab Models
enum class Sections(
    val title: String,
    val icon: Int
) {
    Documentation("Documentation", R.drawable.ic_article),
    Favorites("Favorites", R.drawable.ic_favorite)
}

class TabContent(val section: Sections, val content: @Composable () -> Unit)
// endregion

// region Top App Bar
enum class Action(
    val label: Int,
    val icon: Int
) {
    Search(R.string.action_search, R.drawable.ic_search),
    Settings(R.string.action_settings, R.drawable.ic_settings)
}

class HomeMenuItem(val action: Action, val onClick: () -> Unit)
// endregion

// region Main Content
@Composable
fun DocumentationScreen(
    navigateTo: (Screen) -> Unit
) {
    val documentationSection = TabContent(Sections.Documentation) {
        Text("Documentation", Modifier.padding(16.dp))
    }
    val favoritesSection = TabContent(Sections.Favorites) {
        Text("Favorites", Modifier.padding(16.dp))
    }

    val tabContent = listOf(documentationSection, favoritesSection)
    val (currentSection, updateSection) = rememberSaveable { mutableStateOf(tabContent.first().section) }

    val searchAction = HomeMenuItem(Search) {}
    val settingsAction = HomeMenuItem(Settings) { navigateTo(Screen.Settings) }
    val menuContent = listOf(searchAction, settingsAction)

    Scaffold(
        topBar = {
                 TopAppBar(
                     title = {
                         Text(text = stringResource(id = R.string.documentation_title))
                     },
                     backgroundColor = MaterialTheme.colors.surface,
                     contentColor = MaterialTheme.colors.onSurface,
                     elevation = 0.dp,
                     actions = {
                         menuContent.forEach { menuItem ->
                             IconButton(onClick = menuItem.onClick) {
                                 Icon(
                                     painter = painterResource(id = menuItem.action.icon),
                                     contentDescription = stringResource(menuItem.action.label),
                                     tint = MaterialTheme.colors.onSurface
                                 )
                             }
                         }
                     }
                 )
        },
        bodyContent = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            ) {
                TabContent(currentSection, updateSection, tabContent)
            }
        }
    )
}

// endregion

// region Tab Content
@Composable
private fun TabContent(
    currentSection: Sections,
    updateSection: (Sections) -> Unit,
    tabContent: List<TabContent>
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }

    Column {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            tabContent[selectedTabIndex].content()
        }
        BottomNavigation(
            elevation = 0.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            tabContent.forEachIndexed { index, tabContent ->
                BottomNavigationItem(
                    icon = { Icon(painter = painterResource(id = tabContent.section.icon), contentDescription = "") },
                    label = { Text(tabContent.section.title) },
                    selected = selectedTabIndex == index,
                    onClick = { updateSection(tabContent.section) },
                    unselectedContentColor = MaterialTheme.colors.onBackground,
                    selectedContentColor = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}
// endregion