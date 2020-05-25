/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcherplugin.plugin

import com.android.build.gradle.*
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.hasPlugin

internal val Project.buildVariants: DomainObjectSet<out BaseVariant>
    get() {
        val plugins = project.plugins
        val extensions = project.extensions
        return when {
            plugins.hasPlugin(LibraryPlugin::class) -> extensions.getByType(LibraryExtension::class).libraryVariants
            plugins.hasPlugin(AppPlugin::class) -> extensions.getByType(AppExtension::class).applicationVariants
            plugins.hasPlugin(TestPlugin::class) -> extensions.getByType(TestExtension::class).applicationVariants
            else -> throw GradleException("Unsupported project type")
        }
    }
