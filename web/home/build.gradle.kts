plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.5.0-build228"
}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        jsMain {
            dependencies {
                implementation(project(Module.Domain.path))
                implementation(project(Module.MviCore.path))
                implementation(project(Module.Test.path))
                implementation(project(Module.Core.path))
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(npm("copy-webpack-plugin", "9.0.0"))
                implementation(npm("@material-ui/icons", "4.11.2"))
            }
        }
    }
}
