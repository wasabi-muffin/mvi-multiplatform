buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Jvm, BuildTarget.Js)

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
