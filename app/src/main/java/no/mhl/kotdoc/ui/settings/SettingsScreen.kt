package no.mhl.kotdoc.ui.settings

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen
import no.mhl.kotdoc.ui.settings.model.Group
import no.mhl.kotdoc.ui.settings.model.Setting
import no.mhl.kotdoc.ui.theme.cerulean
import no.mhl.kotdoc.ui.theme.fuchsiaPink
import no.mhl.kotdoc.ui.theme.mediumPurple
import no.mhl.kotdoc.ui.theme.sorbus

// region Main Content
@Composable
fun SettingsScreen(
    navigateTo: (Screen) -> Unit,
    onBack: () -> Unit
) {
    val settings = listOf(
        Group(
            stringResource(id = R.string.setting_group_general),
            listOf(
                Setting(R.string.setting_theme, R.drawable.ic_theme, mediumPurple) { }
            )
        ), 
        Group(
            stringResource(id = R.string.setting_group_information),
            listOf(
                Setting(R.string.setting_about, R.drawable.ic_about, cerulean) { },
                Setting(R.string.setting_tpl, R.drawable.ic_book, fuchsiaPink) { },
                Setting(R.string.setting_feedback, R.drawable.ic_feedback, sorbus) { }
            )
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title)) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
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
                        GroupListItem(data.name)
                        data.settings.forEach { setting ->
                            SettingListItem(setting = setting)
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
fun SettingListItem(setting: Setting) {
    Box(Modifier
        .background(MaterialTheme.colors.surface)
        .fillMaxWidth()
        .clickable { setting.onClick }
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
fun GroupListItem(groupName: String) {
    Box(Modifier
        .fillMaxWidth()
        .padding(start = 16.dp, bottom = 8.dp, top = 32.dp )
    ) {
        Text(text = groupName, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}
// endregion