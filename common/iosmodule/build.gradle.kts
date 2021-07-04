buildTargets = setOf(BuildTarget.Ios)
setupMultiplatform()
setupCocoapods("MVIMultiplatform")

plugins {
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
}

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
            isStatic = false
            transitiveExport = true
            linkerOpts.add("-lsqlite3")
            Module.values().forEach { export(project(it.path)) }
        }
    }

    multiplatformSwiftPackage {
        swiftToolsVersion("5.3")
        targetPlatforms {
            iOS { v("14") }
        }
        outputDirectory(File(project.projectDir, "MVIMultiplatformPackage"))
    }
}
