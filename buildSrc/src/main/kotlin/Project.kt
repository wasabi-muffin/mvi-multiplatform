import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.setupMultiplatform() {
    plugins.apply("kotlin-multiplatform")

    doIfBuildTargetAvailable<BuildTarget.Android> {
        plugins.apply("com.android.library")

        setupAndroidSdkVersions()
    }

    repositories {
        google()
        mavenCentral()
    }

    kotlin {
        doIfBuildTargetAvailable<BuildTarget.Js> {
            js(IR) {
                useCommonJs()
                browser()
            }
        }

        doIfBuildTargetAvailable<BuildTarget.Android> {
            android {
                // publishLibraryVariants("release", "debug")
            }
        }

        doIfBuildTargetAvailable<BuildTarget.Jvm> {
            jvm()
        }

        doIfBuildTargetAvailable<BuildTarget.Ios> {
            val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
            if (onPhone) {
                iosArm64("ios")
            } else {
                iosX64("ios")
            }
        }

        doIfBuildTargetAvailable<BuildTarget.MacOsX64> {
            macosX64()
        }

        sourceSets {
            all {
                languageSettings.apply {
                    useExperimentalAnnotation("kotlin.RequiresOptIn")
                    useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
                }
            }

            commonMain {
                dependencies {
                    projectDependencies.forEach { implementation(project(it.path)) }
                }
            }
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf(
                "-Xskip-prerelease-check",
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
            )
        }
    }
}

fun Project.setupAndroidSdkVersions() {
    androidConfig {

        compileSdkVersion(Versions.androidCompileSdk)

        defaultConfig {
            targetSdk = Versions.androidTargetSdk
            minSdk = Versions.androidMinSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        lintOptions {
            isWarningsAsErrors = true
            isAbortOnError = false
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

fun Project.setupCocoapods(framework: String = name.capitalize()) {
    kotlin {
        plugins.apply("org.jetbrains.kotlin.native.cocoapods")

        cocoapodsConfig {
            summary = "MVIMultiplatform"
            homepage = "https://github.com/gmvalentino/mvi-multiplatform"
            frameworkName = framework
        }
    }
}

fun KotlinMultiplatformExtension.cocoapodsConfig(block: CocoapodsExtension.() -> Unit) {
    (this as ExtensionAware).extensions.getByType<CocoapodsExtension>()
        .block()
}

fun Project.androidConfig(block: BaseExtension.() -> Unit) {
    extensions.getByType<BaseExtension>().block()
}

fun Project.kotlin(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.getByType<KotlinMultiplatformExtension>().block()
}

fun KotlinMultiplatformExtension.sourceSets(block: SourceSets.() -> Unit) {
    sourceSets.block()
}
