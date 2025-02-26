plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.flutter.loader) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.layout.buildDirectory.set(file("../build"))

subprojects{
    project.layout.buildDirectory.set(file("${rootProject.layout.buildDirectory.orNull}/${project.name}"))
}

subprojects{
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
