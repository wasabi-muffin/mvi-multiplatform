plugins {
    id("kotlin-platform-jvm")
    application
    id("kotlinx-serialization")
}

dependencies {
    implementation(project(":common:data:models"))

    with(Deps.Kotlinx) {
        implementation(serializationCore) // JVM dependency
        implementation(coroutinesCore)
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}