/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcherplugin.plugin.tasks

import com.android.build.gradle.api.BaseVariant
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import org.gradle.api.DefaultTask
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.property
import java.util.*


@Suppress("UnstableApiUsage")
open class CreateThemesTask @javax.inject.Inject constructor(objects: ObjectFactory) :
    DefaultTask() {

    @get:OutputDirectory
    val outputSrcDir = objects.directoryProperty()

    @get:Input
    var className = objects.property<String>().value("AssetFileKt")

    @get:InputFiles
    val xmlFilesCollection = objects.fileCollection()

    @TaskAction
    fun generateKotlinFile() {

        val listOfThemeInfo: ParameterizedTypeName = List::class.parameterizedBy(String::class)
        val listOf = MemberName("kotlin.collections", "listOf")

        val themeInfos =
            xmlFilesCollection.files.joinToString { "ThemeInfo(\"${it.nameWithoutExtension}\")" }
        logger.lifecycle("generateKotlinFile: $themeInfos")

        val predefinedThemesInfo = PropertySpec.builder("predefinedThemesInfo", listOfThemeInfo)
            .addModifiers(KModifier.INTERNAL)
            .initializer("%M($themeInfos)", listOf)
            .build()


        // generating kt file
        FileSpec.builder(javaClass.`package`.name, className.get())
            .addProperty(predefinedThemesInfo)
            .build()
            .writeTo(outputSrcDir.asFile.get())

        logger.lifecycle("generating asset kotlin file ${javaClass.`package`.name}.${className.get()} in ${outputSrcDir.asFile.get()}")
    }

    companion object {
        private const val TASK_PREFIX_NAME = "readThemes"

        @OptIn(ExperimentalStdlibApi::class)
        internal fun taskNameForVariant(variant: BaseVariant): String =
            TASK_PREFIX_NAME + variant.name.capitalize(Locale.ROOT)
    }
}
