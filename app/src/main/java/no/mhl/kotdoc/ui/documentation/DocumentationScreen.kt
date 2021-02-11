package no.mhl.kotdoc.ui.documentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen

@Composable
fun DocumentationScreen(
    navigateTo: (Screen) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
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
                Text(
                    text = "Documentation Screen",
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    )
}