package no.mhl.kotdoc.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import no.mhl.kotdoc.data.AppContainer
import no.mhl.kotdoc.ui.theme.KotDocTheme

// region App Entry
@Composable
fun KotDocApp(container: AppContainer, navigationViewModel: NavigationViewModel) {
    KotDocTheme {
        AppContent(navigationViewModel)
    }
}
// endregion

@Composable
private fun AppContent(navigationViewModel: NavigationViewModel) {
    Surface(color = MaterialTheme.colors.error) {
        Text("Lets do it?")
    }
}
