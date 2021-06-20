object Versions1 {
    val androidMinSdk = 21
    val androidCompileSdk = 29
    val androidTargetSdk = androidCompileSdk

    const val kotlin = "1.5.10"
    const val gradle = "4.2.0"

    val buildToolsVersion = "30.0.3"
    val coroutines = "1.5.0"
    val kermit = "0.1.9"
    val koin = "3.0.2"
    val ktlint_gradle_plugin = "10.0.0"
    val ktor = "1.6.0"
    val junit = "4.13.2"
    val material = "1.3.0"
    val desugarJdkLibs = "1.1.5"
    val multiplatformSettings = "0.7.7"
    val robolectric = "4.5.1"
    val sqlDelight = "1.5.0"
    val stately = "1.1.7"
    val serialization = "1.2.1"
    val kotlinxDateTime = "0.2.1"
    val turbine = "0.5.1"

    object AndroidX {
        val appcompat = "1.3.0"
        val constraintlayout = "2.0.4"
        val core = "1.5.0"
        val lifecycle = "2.3.1"
        val recyclerview = "1.2.0"
        val swipeRefresh = "1.1.0"
        val test = "1.3.0"
        val test_ext = "1.1.2"
    }
}

object Deps1 {
    val android_gradle_plugin = "com.android.tools.build:gradle:${Versions1.gradle}"
    val junit = "junit:junit:${Versions1.junit}"
    val material = "com.google.android.material:material:${Versions1.material}"
    val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:${Versions1.desugarJdkLibs}"
    val kermit = "co.touchlab:kermit:${Versions1.kermit}"
    val koinAndroid = "io.insert-koin:koin-android:${Versions1.koin}"
    val koinCore = "io.insert-koin:koin-core:${Versions1.koin}"
    val koinTest = "io.insert-koin:koin-test:${Versions1.koin}"
    val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions1.multiplatformSettings}"
    val multiplatformSettingsTest = "com.russhwolf:multiplatform-settings-test:${Versions1.multiplatformSettings}"
    val robolectric = "org.robolectric:robolectric:${Versions1.robolectric}"
    val stately = "co.touchlab:stately-common:${Versions1.stately}"
    val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions1.kotlinxDateTime}"
    val turbine = "app.cash.turbine:turbine:${Versions1.turbine}"

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:${Versions1.AndroidX.appcompat}"
        val core_ktx = "androidx.core:core-ktx:${Versions1.AndroidX.core}"
        val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions1.AndroidX.constraintlayout}"
        val recyclerView = "androidx.recyclerview:recyclerview:${Versions1.AndroidX.recyclerview}"
        val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions1.AndroidX.swipeRefresh}"

        val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions1.AndroidX.lifecycle}"
        val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions1.AndroidX.lifecycle}"
        val lifecycle_viewmodel_extensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions1.AndroidX.lifecycle}"
        val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:${Versions1.AndroidX.lifecycle}"
    }

    object AndroidXTest {
        val core = "androidx.test:core:${Versions1.AndroidX.test}"
        val junit = "androidx.test.ext:junit:${Versions1.AndroidX.test_ext}"
        val runner = "androidx.test:runner:${Versions1.AndroidX.test}"
        val rules = "androidx.test:rules:${Versions1.AndroidX.test}"
    }

    object KotlinTest {
        val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions1.kotlin}"
        val annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions1.kotlin}"
        val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions1.kotlin}"
        val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions1.kotlin}"
    }

    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions1.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions1.coroutines}"
        val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions1.coroutines}"
    }

    object SqlDelight {
        val gradle = "com.squareup.sqldelight:gradle-plugin:${Versions1.sqlDelight}"
        val runtime = "com.squareup.sqldelight:runtime:${Versions1.sqlDelight}"
        val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:${Versions1.sqlDelight}"
        val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:${Versions1.sqlDelight}"
        val driverIos = "com.squareup.sqldelight:native-driver:${Versions1.sqlDelight}"
        val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions1.sqlDelight}"
    }

    object Ktor {
        val serverCore = "io.ktor:ktor-server-core:${Versions1.ktor}"
        val serverNetty = "io.ktor:ktor-server-netty:${Versions1.ktor}"
        val serialization = "io.ktor:ktor-serialization:${Versions1.ktor}"
        val websockets = "io.ktor:ktor-websockets:${Versions1.ktor}"

        val commonCore = "io.ktor:ktor-client-core:${Versions1.ktor}"
        val commonJson = "io.ktor:ktor-client-json:${Versions1.ktor}"
        val commonLogging = "io.ktor:ktor-client-logging:${Versions1.ktor}"
        val androidCore = "io.ktor:ktor-client-okhttp:${Versions1.ktor}"
        val ios = "io.ktor:ktor-client-ios:${Versions1.ktor}"
        val commonSerialization = "io.ktor:ktor-client-serialization:${Versions1.ktor}"
    }
}
