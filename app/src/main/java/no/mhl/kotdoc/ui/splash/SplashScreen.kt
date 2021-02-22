package no.mhl.kotdoc.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import no.mhl.kotdoc.R
import no.mhl.kotdoc.ui.Screen
import java.util.concurrent.TimeUnit

// region Initial Entry
@Composable
fun SplashScreen(
    navigateTo: (Screen) -> Unit
) {
    startScreenDelay(navigateTo)
    SplashScreen()
}

private fun startScreenDelay(
    navigateTo: (Screen) -> Unit
) = CoroutineScope(Dispatchers.Main).launch {
    delay(TimeUnit.SECONDS.toMillis(2))
    navigateTo(Screen.Home)
}
// endregion

// region Main Content
@Preview
@Composable
fun SplashScreen() {
    Scaffold(
        bodyContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MaterialTheme.colors.surface)
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.h4
                )
            }
        }
    )
}
// endregion