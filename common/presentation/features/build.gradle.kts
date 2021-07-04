buildTargets = setOf(BuildTarget.Android, BuildTarget.Ios)
projectDependencies = setOf(
    Module.MviCore,
    Module.Domain,
    Module.Repository,
    Module.Api
)

setupMultiplatform()

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Deps.Kotlinx.coroutinesCore)
                implementation(Deps.Koin.core)
                api(Deps.Log.kermit)
                implementation(Deps.Kotlinx.dateTime)
            }
        }
    }
}
