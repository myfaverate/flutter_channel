pluginManagement {
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
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    val storageUrl: String = System.getenv("FLUTTER_STORAGE_BASE_URL") ?: "https://storage.googleapis.com"
    println("storageUrl: $storageUrl")
    repositories {
        maven {
            url = uri(path = "D:/SoftWare/Android/FlutterProjects/flutter_apk_module/build/host/outputs/repo")
        }
        maven {
            url = uri(path = "$storageUrl/download.flutter.io")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "FlutterIntegration"
include(":app")
include(":home")
