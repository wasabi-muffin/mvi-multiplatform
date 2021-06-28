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

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            transitiveExport = true
            Module.values().forEach { export(project(it.path)) }
            freeCompilerArgs = freeCompilerArgs + "-Xobjc-generics"
        }
    }
}
