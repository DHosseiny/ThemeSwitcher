package davud.hosseini.themeswitcherplugin.plugin.data.helpers

import davud.hosseini.themeswitcherplugin.plugin.data.helpers.wrappers.AndroidExtensionWrapper
import org.gradle.api.Project

class AndroidProjectHelper(val project: Project) {
    val androidExtension: AndroidExtensionWrapper by lazy {
        AndroidExtensionWrapper(project.extensions.getByName("android"))
    }
}