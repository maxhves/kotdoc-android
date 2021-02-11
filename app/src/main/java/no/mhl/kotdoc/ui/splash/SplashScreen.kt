package no.mhl.kotdoc.ui.splash

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import no.mhl.kotdoc.ui.Screen
import no.mhl.kotdoc.ui.Screen.Documentation
import java.util.concurrent.TimeUnit

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
    delay(TimeUnit.SECONDS.toMillis(5))
    navigateTo(Documentation)
}

@Composable
fun SplashScreen() {
    Scaffold(
        bodyContent = { innerPadding ->
            Text("THIS IS THE SPALSH SCREEN")
        }
    )
}