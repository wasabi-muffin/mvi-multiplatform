buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Jvm)

setupMultiplatform()

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.serializationCore)
                implementation(Deps.Kotlinx.dateTime)
            }
        }
    }
}
