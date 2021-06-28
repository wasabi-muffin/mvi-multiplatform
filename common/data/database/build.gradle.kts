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
                implementation(Deps1.SqlDelight.runtime)
                implementation(Deps1.SqlDelight.coroutinesExtensions)
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
            }
        }

        iosMain {
            dependencies {
                implementation(Deps1.SqlDelight.driverIos)
                implementation(Deps1.Coroutines.common) {
                    version {
                        strictly(Versions1.coroutines)
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
