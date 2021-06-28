buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
setupMultiplatform()

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}


kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps1.Coroutines.common)
                implementation(Deps1.stately)
                implementation(Deps1.multiplatformSettings)
                implementation(Deps.Koin.core)
                implementation(Deps1.Ktor.commonSerialization)
                implementation(Deps1.kotlinxDateTime)
                api(Deps1.kermit)
            }
        }
    }
}