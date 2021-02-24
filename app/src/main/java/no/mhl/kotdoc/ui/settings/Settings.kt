package no.mhl.kotdoc.ui.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.theme.cerulean
import no.mhl.kotdoc.ui.theme.fuchsiaPink
import no.mhl.kotdoc.ui.theme.mediumPurple
import no.mhl.kotdoc.ui.theme.sorbus
import no.mhl.kotdoc.ui.settings.Settings.THEME
import no.mhl.kotdoc.ui.settings.Settings.ABOUT
import no.mhl.kotdoc.ui.settings.Settings.LIBRARIES
import no.mhl.kotdoc.ui.settings.Settings.FEEDBACK

enum class Settings(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val iconTint: Color
) {
    THEME(R.string.setting_theme, R.drawable.ic_theme, mediumPurple),
    ABOUT(R.string.setting_about, R.drawable.ic_about, cerulean),
    LIBRARIES(R.string.setting_tpl, R.drawable.ic_book, fuchsiaPink),
    FEEDBACK(R.string.setting_feedback, R.drawable.ic_feedback, sorbus)
}

data class SettingGroup(
    @StringRes val heading: Int,
    val settings: List<Settings>
)

private val settings = listOf(
    SettingGroup(R.string.setting_group_general, listOf(THEME)),
    SettingGroup(R.string.setting_group_information, listOf(ABOUT, LIBRARIES, FEEDBACK))
)

// region Main Content
@Composable
fun Settings(
    selectSetting: (Settings) -> Unit,
    upPress: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(upPress) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = stringResource(R.string.content_desc_on_back),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
            )
        },
        bodyContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                LazyColumn {
                    items(settings) { data ->
                        GroupListItem(stringResource(data.heading))
                        data.settings.forEach { setting ->
                            SettingListItem(setting, selectSetting)
                        }
                    }
                }
            }
        }
    )
}
// endregion

// region Setting List Views
@Composable
private fun SettingListItem(
    setting: Settings,
    selectSetting: (Settings) -> Unit
) {
    Box(Modifier
        .background(MaterialTheme.colors.surface)
        .fillMaxWidth()
        .clickable { selectSetting(setting) }
        .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = setting.icon),
                contentDescription = stringResource(setting.label),
                tint = setting.iconTint
            )
            Text(
                text = stringResource(setting.label),
                modifier = Modifier.weight(1f).padding(start = 16.dp)
            )
            Icon(painter = painterResource(id = R.drawable.ic_chevron_right), contentDescription = "")
        }
    }
}

@Composable
private fun GroupListItem(groupName: String) {
    Box(Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, bottom = 8.dp, top = 32.dp )
    ) {
        Text(text = groupName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}
// endregion