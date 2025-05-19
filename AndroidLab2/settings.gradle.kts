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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidLab2"
include(":app")
include(":ch1")
include(":ch2")
include(":ch3")
include(":ch3_outer")
include(":ch4")
include(":ch5")
include(":ch5_outer")
include(":ch6")
include(":ch6_outer")
include(":ch7")
