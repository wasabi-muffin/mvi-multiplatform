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
        compilations.forEach {
            it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
        }
        binaries.withType<org.jetbrains.kotlin.gradle.plugin.mpp.Framework> {
            isStatic = true
            transitiveExport = true
            Module.values().forEach { export(project(it.path)) }
        }
    }

    tasks.register("replaceCocoapods", Copy::class) {
        mustRunAfter(":common:iosmodule:podspec")

        val dir = project.rootDir.resolve("common/iosmodule/")
        dir.walk()
            .filter { it.name.contains(".podspec") }
            .forEach { it.writeText(it.readText()
                .replace("spec.libraries                = \"c++\"",
                    "spec.libraries                = \"c++\", \"sqlite3\""))
            }
    }
}
