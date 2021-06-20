buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps1.SqlDelight.runtime)
                implementation(Deps1.SqlDelight.coroutinesExtensions)
                implementation(Deps1.koinCore)
                implementation(Deps1.Ktor.commonSerialization)
                implementation(Deps1.kotlinxDateTime)
                api(Deps1.kermit)
                implementation(project(":common:domain"))
                implementation(project(":common:data:api"))
                implementation(project(":common:data:database"))
                implementation(project(":common:data:repository"))
                implementation(project(":common:data:settings"))
                implementation(project(":common:presentation:core"))
                implementation(project(":common:presentation:features"))
            }
        }

        androidMain {
            dependencies {
                implementation(kotlin("stdlib", Versions1.kotlin))
                implementation(Deps1.SqlDelight.driverAndroid)
                implementation(Deps1.Coroutines.android)
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

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            export(Deps1.kermit)
            transitiveExport = true
        }
    }
}
