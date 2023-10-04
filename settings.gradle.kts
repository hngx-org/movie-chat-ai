pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
      /*  maven {
            url = uri("https://www.jitpack.io")
        }*/

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

    }
}



rootProject.name = "MovieChat"
include(":app")
 