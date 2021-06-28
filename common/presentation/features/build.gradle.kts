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
                implementation(Deps1.Coroutines.common)
                implementation(Deps1.stately)
                implementation(Deps.Koin.core)
                api(Deps1.kermit)
                implementation(Deps.Kotlinx.dateTime)
            }
        }
    }
}
