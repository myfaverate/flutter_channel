pluginManagement {
    val localPropertiesFile: File = file("local.properties")
    val keyStoreProperties = java.util.Properties().apply { load(java.io.FileInputStream(localPropertiesFile)) }
    val flutterSdkPath: String = keyStoreProperties["flutter.sdk"] as String
    includeBuild("${flutterSdkPath}/packages/flutter_tools/gradle")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":app")
 