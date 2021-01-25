package no.mhl.kotdoc

import android.app.Application
import no.mhl.kotdoc.data.AppContainer
import no.mhl.kotdoc.data.AppContainerImpl

class KotDocApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

}