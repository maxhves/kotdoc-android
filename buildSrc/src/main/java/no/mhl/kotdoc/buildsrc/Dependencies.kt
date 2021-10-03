package no.mhl.kotdoc.buildsrc

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.0-alpha12"

    object Kotlin {
        const val version = "1.4.30"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"

        const val ktx = "androidx.core:core-ktx:1.5.0-beta02"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1"
    }

    object AndroidX {
        const val material = "com.google.android.material:material:1.3.0"
        const val appCompat = "androidx.appcompat:appcompat:1.3.0-beta01"

        const val navigation = "androidx.navigation:navigation-compose:1.0.0-alpha08"
        const val activity = "androidx.activity:activity-compose:1.3.0-alpha03"

        object Compose {
            const val version = "1.0.0-beta01"

            const val ui = "androidx.compose.ui:ui:$version"
            const val material = "androidx.compose.material:material:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$version"
        }

        object Lifecycle {
            private const val version = "2.3.0"

            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }
    }

    object Hilt {
        private const val version = "2.37"

        const val daggerHiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val android = "com.google.dagger:hilt-android:$version"
        const val kapt = "com.google.dagger:hilt-compiler:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"

        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object MarkdownParser {
        const val local = ":markdownparser"
    }

}
