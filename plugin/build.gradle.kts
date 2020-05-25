plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

repositories {
    jcenter()
    google()
}

group = "com.github.dhosseiny.theme-switcher"
version = "0.1"

dependencies {
    implementation("com.squareup:kotlinpoet:1.5.0")
    compileOnly("com.android.tools.build:gradle:3.6.3")
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit:junit:4.13")
}

gradlePlugin {
    plugins {
        create("theme-switcher-plugin") {
            id = "com.github.dhosseiny.theme-switcher.plugin"
            displayName = "Theme Switcher Gradle Plugin"
            description =
                "Theme Switcher Gradle Plugin - Generates kotlin files from xml color files to use in databinding and code expressions"
            implementationClass = "davud.hosseini.themeswitcherplugin.plugin.ThemeSwitcherPlugin"
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

//pluginBundle {
//    website = "https://github.com/DHosseiny/ThemeSwitcher"
//    vcsUrl = "https://github.com/DHosseiny/ThemeSwitcher"
//
//    tags = listOf("theme-switcher", "theme", "android theme", "xml", "databinding")
//}

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