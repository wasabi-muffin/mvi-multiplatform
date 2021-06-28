buildTargets = setOf(BuildTarget.Ios)
setupMultiplatform()
setupCocoapods(framework = "MVIMultiplatform")

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                Module.values().forEach { api(project(it.path)) }
            }
        }

        iosMain { }
    }

    // // Configure the framework which is generated internally by cocoapods plugin
    // targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
    //     binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
    //         isStatic = true
    //         transitiveExport = true
    //         Module.values().forEach { export(project(it.path)) }
    //     }
    // }

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            transitiveExport = true
            Module.values().forEach { export(project(it.path)) }
        }
    }
}
