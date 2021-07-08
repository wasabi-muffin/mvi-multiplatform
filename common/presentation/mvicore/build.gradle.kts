buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios, BuildTarget.Js)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(Module.Domain.path)) // TODO: Used for example, remove later
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                api(Deps.Log.kermit)
            }
        }
    }
}
