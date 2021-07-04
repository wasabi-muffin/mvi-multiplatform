buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
projectDependencies = setOf(
    Module.Models,
    Module.Repository
)
plugins {
    id("com.squareup.sqldelight")
}

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.SqlDelight.coroutineExtensions)
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

sqldelight {
    database("Db") {
        packageName = "com.gmvalentino.db"
    }
}
