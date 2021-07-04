buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                implementation(Deps.Ktor.serialization)
                implementation(Deps.Kotlinx.dateTime)
                api(Deps.Log.kermit)
            }
        }
    }
}