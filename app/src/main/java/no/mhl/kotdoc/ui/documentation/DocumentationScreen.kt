package no.mhl.kotdoc.ui.documentation

import androidx.compose.material.Scaffold
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen

@Composable
fun DocumentationScreen(
    navigateTo: (Screen) -> Unit
) {
    Scaffold(
        topBar = {
            val title = stringResource(id = R.string.app_name)
            TopAppBar(
                title = { Text(text = title) },
                backgroundColor = Color.White
            )
        },
        bodyContent = { innerPadding ->
            Text("is it working??")
        }
    )
}