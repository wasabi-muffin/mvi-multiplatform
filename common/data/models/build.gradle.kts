buildTargets = setOf(BuildTarget.Ios, BuildTarget.Jvm, BuildTarget.Js)

setupMultiplatform()

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
