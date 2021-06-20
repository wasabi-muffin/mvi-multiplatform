plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(Versions1.androidCompileSdk)
    buildToolsVersion = Versions1.buildToolsVersion
    defaultConfig {
        applicationId = "com.gmvalentino"
        minSdkVersion(Versions1.androidMinSdk)
        targetSdkVersion(Versions1.androidTargetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            // proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isWarningsAsErrors = true
        isAbortOnError = true
    }

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    implementation(project(":common:domain"))
    implementation(project(":common:presentation:features"))
    implementation(project(":common:presentation:core"))
    implementation(project(":common:core"))
    implementation(Deps1.AndroidX.recyclerView)
    implementation(Deps1.AndroidX.swipeRefresh)
    implementation(Deps1.material)
    coreLibraryDesugaring(Deps1.desugarJdkLibs)
    implementation(Deps1.AndroidX.appcompat)
    implementation(Deps1.AndroidX.core_ktx)
    implementation(Deps1.Ktor.androidCore)
    implementation(Deps1.AndroidX.constraintlayout)
    implementation(Deps1.SqlDelight.runtimeJdk)
    implementation(Deps1.SqlDelight.driverAndroid)
    implementation(Deps1.Coroutines.common)
    implementation(Deps1.Coroutines.android)
    implementation(Deps1.multiplatformSettings)
    implementation(Deps1.koinCore)
    implementation(Deps1.koinAndroid)
    implementation(Deps1.AndroidX.lifecycle_runtime)
    implementation(Deps1.AndroidX.lifecycle_viewmodel)
    implementation(Deps1.AndroidX.lifecycle_viewmodel_extensions)
    implementation(Deps1.AndroidX.lifecycle_livedata)
    testImplementation(Deps1.junit)
}
