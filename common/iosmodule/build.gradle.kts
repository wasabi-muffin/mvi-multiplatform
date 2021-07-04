buildTargets = setOf(BuildTarget.Ios)
setupMultiplatform()
setupCocoapods("MVIMultiplatform")

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
        compilations.forEach {
            it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
        }
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            transitiveExport = true
            linkerOpts.add("-lsqlite3")
            Module.values().forEach { export(project(it.path)) }
        }
    }
}
