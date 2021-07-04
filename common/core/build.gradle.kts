buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Js)
projectDependencies = setOf(
    Module.Domain,
    Module.Api,
    Module.Database,
    Module.Repository,
    Module.Settings,
    Module.Models,
    Module.MviCore,
    Module.Features
)
setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.SqlDelight.coroutineExtensions)
                implementation(Deps.Koin.core)
                implementation(Deps.Ktor.clientSerialization)
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
