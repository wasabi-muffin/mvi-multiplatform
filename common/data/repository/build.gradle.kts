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
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                implementation(Deps.Kotlinx.serializationCore)
                implementation(Deps.Kotlinx.dateTime)
                api(Deps.Log.kermit)
            }
        }

        androidMain {
            dependencies {
                implementation(kotlin("stdlib", Versions.kotlin))
                implementation(Deps.SqlDelight.androidDriver)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.SqlDelight.nativeDriver)

                implementation(Deps.Kotlinx.coroutinesCore) {
                    version {
                        strictly(Versions.kotlinCoroutines)
                    }
                }
            }
        }
    }
}