package no.mhl.kotdoc.ui.settings.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R

@Composable
fun SettingDetail(
    upPress: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
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
        Text(text = "Settings Detail Screen", modifier = Modifier.padding(16.dp))
    }
}