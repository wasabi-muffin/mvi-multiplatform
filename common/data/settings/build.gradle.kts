buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
setupMultiplatform()

plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                implementation(Deps.Ktor.serialization)
                implementation(Deps.Kotlinx.dateTime)
                implementation(Deps.MultiplatformSettings.core)
                api(Deps.Log.kermit)
            }
        }
    }
}