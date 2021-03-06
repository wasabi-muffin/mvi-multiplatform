buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Js)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                implementation(Deps.Kotlinx.serializationCore)
                implementation(Deps.Kotlinx.dateTime)
                api(Deps.Log.kermit)
            }
        }
    }
}