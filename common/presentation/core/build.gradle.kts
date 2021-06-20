import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps1.Coroutines.common)
                implementation(Deps1.stately)
                implementation(Deps1.koinCore)
                api(Deps1.kermit)
            }
        }
    }

    // Configure the framework which is generated internally by cocoapods plugin
    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = true
            export(Deps1.kermit)
            transitiveExport = true
        }
    }
}
