package davud.hosseini.themeswitcherplugin.plugin

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import davud.hosseini.themeswitcherplugin.plugin.ProjectScopedConfiguration.Companion.checkAndroidPlugin
import davud.hosseini.themeswitcherplugin.plugin.model.ThemeSwitcherExtension
import davud.hosseini.themeswitcherplugin.plugin.tasks.CreateThemesTask
import davud.hosseini.themeswitcherplugin.plugin.tasks.CreateThemesTask.Companion.taskNameForVariant
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.register
import java.io.File

class ThemeSwitcherPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        ProjectScopedConfiguration(project).apply()
        project.configureDependencies()
    }

    private fun Project.configureDependencies() {
        dependencies {

            project.checkAndroidPlugin()
            //            add("androidTestImplementation", "espressoCore")//TODO: Add theme switcher library
        }
    }
}

private class ProjectScopedConfiguration(private val project: Project) {

    /**
     * <builddir>/generated/themeSwitcher/src
     */
    private val rootGeneratedBuildDir =
        project.buildDir.resolve("generated").resolve("themeSwitcher").resolve("src")

    fun apply() {

        project.checkAndroidPlugin()

        val extension =
            project.extensions.create(EXTENSION_NAME, ThemeSwitcherExtension::class.java)
        val colorsFilesPath = extension.colorsDirRelativePath

        project.afterEvaluate {
            buildVariants.configureEach {
                configureKotlinTask(this, colorsFilesPath)
            }
        }
    }

    private fun configureKotlinTask(variant: BaseVariant, colorsFilesPath: String) {

        val outputSrcDir = getGeneratedKotlinOutputDirForVariant(variant.name)


        val task = project.tasks.register<CreateThemesTask>(taskNameForVariant(variant)) {
            xmlFilesCollection.setFrom(variant.colorFilesOfVariant(colorsFilesPath))
            this.outputSrcDir.set(outputSrcDir)

            project.logger.debug(
                "Configured kotlin generation task for [${variant.name}] variant set\n" +
                        "Registered new kotlin source directory - $outputSrcDir"
            )
        }


        // Have to configure task here until agp supports task provider https://issuetracker.google.com/issues/150799913
        variant.registerJavaGeneratingTask(task.get(), outputSrcDir)
    }

    private fun BaseVariant.colorFilesOfVariant(colorsFilesDirPath: String): List<File> =
        sourceSets
            .flatMap {
                it.assetsDirectories
            }.map {
                File(it.path + File.separator + colorsFilesDirPath)
            }.flatMap {
                it.xmlFilesInDir()
            }
            .distinct() //TODO: Decide about what to do when there is repetitive files

    /**
     * Returns kotlin source root directory where files will be generated for given variant.
     *
     * ex. <Project>/build/generated/assetsjournalist/src/<main>/kotlin
     */
    private fun getGeneratedKotlinOutputDirForVariant(variantName: String) = rootGeneratedBuildDir
        .resolve(variantName).resolve("kotlin")


    companion object {
        internal const val TASKS_GROUP_NAME = "themeSwitcher"
        internal const val EXTENSION_NAME = "themeSwitcher"


        internal fun Project.checkAndroidPlugin() {
            if (project.extensions.findByType<BaseExtension>() == null) {
                throw GradleException("Failed to locate android plugin extension, make sure plugin is applied after android gradle plugin")
            }
        }

    }
}