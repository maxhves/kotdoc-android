package no.mhl.kotdoc.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.theme.mediumPurple
import no.mhl.kotdoc.ui.theme.sorbus
import no.mhl.kotdoc.ui.utils.*

private enum class DocTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    DOCUMENTATION(R.string.documentation_title, R.drawable.ic_book),
    FAVORITES(R.string.favorites_title, R.drawable.ic_favorite)
}

private enum class DocMenuActions(
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    SEARCH(R.string.action_search, R.drawable.ic_search),
    SETTINGS(R.string.action_settings, R.drawable.ic_settings)
}

// region Main Content
@Composable
fun Home(
    model: HomeViewModel,
    openSettings: () -> Unit,
    openSearch: () -> Unit
) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(DocTabs.DOCUMENTATION) }
    val tabs = DocTabs.values()
    val actions = DocMenuActions.values()

    // TODO Remove test code below
    val doc by model.testGetFile().observeAsState()

    val elements = parseMarkdown(doc?.charStream())


    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(selectedTab.title)) },
                backgroundColor = MaterialTheme.colors.surface,
                actions = {
                    actions.forEach { action ->
                        IconButton({
                            when (action) {
                                DocMenuActions.SEARCH -> openSearch()
                                DocMenuActions.SETTINGS -> openSettings()
                            }
                        }
                        ) {
                            Icon(
                                painterResource(action.icon),
                                stringResource(action.label),
                                tint = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                        label = { Text(stringResource(tab.title)) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.onSurface,
                        unselectedContentColor = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(16.dp)
            .padding(innerPadding)

        LazyColumn(
            modifier = modifier
        ) {
            items(elements) { element ->
                when (element) {
                    is H2 -> H2Text(element.content)
                    is H3 -> H3Text(element.content)
                    is Info -> InfoBox(element.content)
                    is CodeBlock -> CodeBox(element.content)
                    else -> BodyText(element.content)
                }
            }
        }

//        when (selectedTab) {
//            DocTabs.DOCUMENTATION -> Documentation(modifier)
//            DocTabs.FAVORITES -> Favorites(modifier)
//        }
    }
}
// endregion

@Composable
fun H2Text(text: String) {
    Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun H3Text(text: String) {
    Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
fun BodyText(text: String) {
    Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Normal)
}

@Composable
fun InfoBox(text: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color(171, 237, 159, 100),
        elevation = 0.dp
    ) {
        Box(Modifier.padding(16.dp)) {
            Text(text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun CodeBox(text: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Box(Modifier.padding(16.dp)) {
            Text(text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = mediumPurple,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}