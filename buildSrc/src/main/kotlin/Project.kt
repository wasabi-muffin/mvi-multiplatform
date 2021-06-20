import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.setupMultiplatform() {
    plugins.apply("kotlin-multiplatform")

    doIfBuildTargetAvailable<BuildTarget.Android> {
        plugins.apply("com.android.library")

        setupAndroidSdkVersions()
    }

    setupAndroidSdkVersions()

    repositories {
        google()
        mavenCentral()
    }

    kotlin {
        doIfBuildTargetAvailable<BuildTarget.Js> {
            js {
                nodejs()
                browser()

                compilations.all {
                    compileKotlinTask.kotlinOptions {
                        metaInfo = true
                        sourceMap = true
                        sourceMapEmbedSources = "always"
                        moduleKind = "umd"
                        main = "call"
                    }
                }
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
            iosX64()
            iosArm64()
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

        compileSdkVersion(Versions1.androidCompileSdk)

        defaultConfig {
            targetSdkVersion(Versions1.androidTargetSdk)
            minSdkVersion(Versions1.androidMinSdk)
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        lintOptions {
            isWarningsAsErrors = true
            isAbortOnError = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
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