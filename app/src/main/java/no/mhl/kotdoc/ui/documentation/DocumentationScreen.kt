package no.mhl.kotdoc.ui.documentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen
import no.mhl.kotdoc.ui.theme.black
import no.mhl.kotdoc.ui.theme.blackTransparent

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
    val (currentSection, updateSection) = savedInstanceState { tabContent.first().section }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.documentation_title))
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.Black,
                elevation = 0.dp
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
            elevation = 0.dp
        ) {
            tabContent.forEachIndexed { index, tabContent ->
                BottomNavigationItem(
                    icon = { Icon(vectorResource(id = tabContent.section.icon)) },
                    label = { Text(tabContent.section.title) },
                    selected = selectedTabIndex == index,
                    onClick = {
                        updateSection(tabContent.section)
                    },
                    unselectedContentColor = blackTransparent,
                    selectedContentColor = black
                )
            }
        }
    }
}
// endregion