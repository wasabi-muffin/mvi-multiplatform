buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
projectDependencies = setOf(
    Module.Domain,
    Module.Models
)
setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps1.Coroutines.common)
                implementation(Deps.Koin.core)
                implementation(Deps1.Ktor.commonSerialization)
                implementation(Deps1.kotlinxDateTime)
                api(Deps1.kermit)
            }
        }

        androidMain {
            dependencies {
                implementation(kotlin("stdlib", Versions1.kotlin))
                implementation(Deps1.SqlDelight.driverAndroid)
                implementation(Deps1.Coroutines.android)
                implementation(Deps1.Ktor.androidCore)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps1.SqlDelight.driverIos)
                implementation(Deps1.Ktor.ios)

                implementation(Deps1.Coroutines.common) {
                    version {
                        strictly(Versions1.coroutines)
                    }
                }
            }
        }
    }
}