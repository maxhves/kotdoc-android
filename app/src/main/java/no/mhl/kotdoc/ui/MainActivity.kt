package no.mhl.kotdoc.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import no.mhl.kotdoc.ui.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    // region Initialisation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotDocApp(onBackPressedDispatcher, homeViewModel)
        }
    }
    // endregion

}