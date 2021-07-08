include(":common:core")
include(":common:domain")
include(":common:presentation:mvicore")
include(":common:presentation:test")
include(":common:data:api")
include(":common:data:repository")
include(":common:data:database")
include(":common:data:settings")
include(":common:data:models")
include(":common:iosmodule")
include(":android:app")
include(":backend")
include(":web:home")

rootProject.name = "mvi-multiplatform"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
include(":common:presentation:overview")
