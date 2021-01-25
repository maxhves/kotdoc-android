package no.mhl.kotdoc.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import no.mhl.kotdoc.KotDocApplication

class MainActivity : AppCompatActivity() {

    private val navigationViewModel by viewModels<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as KotDocApplication).container
        setContent {
            KotDocApp(appContainer, navigationViewModel)
        }
    }

}