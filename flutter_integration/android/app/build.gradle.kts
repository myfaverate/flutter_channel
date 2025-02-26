import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    alias(libs.plugins.flutter.plugin)
}

val localPropertiesFile: File = rootProject.file("local.properties")
val localProperties = Properties().apply { load(FileInputStream(localPropertiesFile)) }

val flutterVersionCode: String = localProperties["flutter.versionCode"] as? String? ?: "1"
val flutterVersionName: String = localProperties["flutter.versionName"] as? String? ?: "1.0"

android {
    namespace = "edu.tyut.flutter_integration"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    defaultConfig {
        applicationId = "edu.tyut.flutter_integration"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutterVersionCode.toIntOrNull() ?: 1
        versionName = flutterVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_21.target
    }
}

flutter {
    source = "../.."
}
