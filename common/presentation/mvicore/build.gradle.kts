buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Js)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                api(Deps.Log.kermit)
            }
        }
    }
}
