buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps1.Coroutines.common)
                implementation(Deps1.stately)
                implementation(Deps.Koin.core)
                api(Deps1.kermit)
            }
        }
    }
}
