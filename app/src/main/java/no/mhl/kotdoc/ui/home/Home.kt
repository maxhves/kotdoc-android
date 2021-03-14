package no.mhl.kotdoc.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.data.local.Block
import no.mhl.kotdoc.data.local.BlockParser
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

    // TODO Test Case
    val doc by model.testGetFile().observeAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.surface,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(selectedTab.title), style = MaterialTheme.typography.h4) },
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

        // TODO Test Case
        doc?.charStream()?.let { reader ->
            val blocks: List<Block> = BlockParser().parseToBlocks(reader.readLines())

            Column(Modifier.verticalScroll(ScrollState(0)).padding(innerPadding)) {
                MarkdownBlocks(blocks)
            }
        }

//        when (selectedTab) {
//            DocTabs.DOCUMENTATION -> Documentation(modifier)
//            DocTabs.FAVORITES -> Favorites(modifier)
//        }
    }
}
// endregion