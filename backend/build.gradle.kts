plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

application {
    mainClass.set("com.gmvalentino.backend.MainKt")
}

dependencies {
    implementation(project(Module.Models.path))

    with(Deps.Kotlinx) {
        implementation(serializationCore) // JVM dependency
        implementation(coroutinesCore)
        implementation(dateTime)
    }

    with(Deps.Ktor) {
        implementation(serverCore)
        implementation(serverNetty)
        implementation(serialization)
        implementation(websockets)
    }

    with(Deps.Exposed) {
        implementation(core)
        implementation(dao)
        implementation(jdbc)
    }

    with(Deps.Log) {
        implementation(logback)
    }
}