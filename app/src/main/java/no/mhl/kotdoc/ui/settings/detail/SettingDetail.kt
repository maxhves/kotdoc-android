package no.mhl.kotdoc.ui.settings.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.settings.Settings

@Composable
fun SettingDetail(
    settingId: Int,
    upPress: () -> Unit
) {
    val setting = Settings.fromId(settingId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(setting.label)) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(upPress) {
                        Icon(
                            painterResource(R.drawable.ic_back),
                            stringResource(R.string.content_desc_on_back),
                            tint = MaterialTheme.colors.onSurface
                        )
                    }
                }
            )
        }
    ) {
        when (setting) {
            Settings.THEME -> Theme()
            Settings.ABOUT -> About()
            Settings.LIBRARIES -> Libraries()
            Settings.FEEDBACK -> Feedback()
        }
    }
}

@Composable
fun Theme() {
    Text("Theme setting goes here", modifier = Modifier.padding(16.dp))
}

@Composable
fun About() {
    Text("About setting goes here", modifier = Modifier.padding(16.dp))
}

@Composable
fun Libraries() {
    Text("Library setting goes here", modifier = Modifier.padding(16.dp))
}

@Composable
fun Feedback() {
    Text("Feedback setting goes here", modifier = Modifier.padding(16.dp))
}