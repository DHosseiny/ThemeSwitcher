plugins {
    id("com.gradle.plugin-publish") version "0.11.0"
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    jcenter()
    google()
}

group = "com.github.dhosseiny.themeswitcher"
version = "0.1"

dependencies {
    implementation("com.squareup:kotlinpoet:1.5.0")
    compileOnly("com.android.tools.build:gradle:3.6.3")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    plugins {
        create("themeswitcher-plugin") {
            id = "com.github.dhosseiny.themeswitcher.plugin"
            displayName = "Theme Switcher Gradle Plugin"
            description =
                "Theme Switcher Gradle Plugin - Generates kotlin files from xml color files to use in databinding and code expressions"
            implementationClass = "com.github.dhosseiny.themeshitcher.plugin.ThemeSwitcherPlugin"
        }
    }
}

// Add a source set for the functional test suite
//val functionalTestSourceSet = sourceSets.create("functionalTest") {
//}

//gradlePlugin.testSourceSets(functionalTestSourceSet)
//configurations.getByName("functionalTestImplementation")
//    .extendsFrom(configurations.getByName("testImplementation"))
//
// Add a task to run the functional tests
//val functionalTest by tasks.creating(Test::class) {
//    testClassesDirs = functionalTestSourceSet.output.classesDirs
//    classpath = functionalTestSourceSet.runtimeClasspath
//}

//val check by tasks.getting(Task::class) {
//    // Run the functional tests as part of `check`
//    dependsOn(functionalTest)
//}

pluginBundle {
    website = "https://github.com/DHosseiny/ThemeSwitcher"
    vcsUrl = "https://github.com/DHosseiny/ThemeSwitcher"

    tags = listOf("themeswitcher", "theme", "android theme", "xml", "databinding")
}

//uploadArchives {
//    repositories {
//        mavenDeployer {
//            repository(url: uri('/'))
//        }
//    }
//}

//tasks.named<Upload>("uploadArchives") {
//    repositories.withGroovyBuilder {
//        "mavenDeployer" {
//            "repository"("url" to "file://localhost/tmp/myRepo/")
//        }
//    }
//}