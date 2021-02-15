package no.mhl.kotdoc.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen
import no.mhl.kotdoc.ui.documentation.DocumentationScreen
import no.mhl.kotdoc.ui.favorites.FavoritesScreen
import no.mhl.kotdoc.ui.home.model.Action
import no.mhl.kotdoc.ui.home.model.HomeMenuItem
import no.mhl.kotdoc.ui.home.model.Section
import no.mhl.kotdoc.ui.home.model.TabContent

private val tabContent = listOf(
    TabContent(Section.Documentation) { DocumentationScreen() },
    TabContent(Section.Favorites) { FavoritesScreen() }
)

// region Main Content
@Composable
fun HomeScreen(
    navigateTo: (Screen) -> Unit
) {
    val menuContent = listOf(
        HomeMenuItem(Action.Search) {},
        HomeMenuItem(Action.Settings) { navigateTo(Screen.Settings) }
    )

    val (currentSection, updateSection) = rememberSaveable{ mutableStateOf(tabContent.first().section) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.documentation_title)) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp,
                actions = {
                    menuContent.forEach { menuItem ->
                        IconButton(menuItem.onClick) {
                            Icon(
                                painter = painterResource(id = menuItem.action.icon),
                                contentDescription = stringResource(id = menuItem.action.description),
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
            )
        },
        bodyContent = {
            Box(Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
            ) {
                TabContent(currentSection, updateSection)
            }
        }
    )
}
// endregion

// region Tab Content
@Composable
private fun TabContent(
    currentSection: Section,
    updateSection: (Section) -> Unit
) {
    val selectedTabIndex = tabContent.indexOfFirst { it.section == currentSection }

    Column {
        Box(modifier = Modifier.weight(1f)) {
            tabContent[selectedTabIndex].content()
        }
        BottomNavigation(elevation = 0.dp, backgroundColor = MaterialTheme.colors.surface) {
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