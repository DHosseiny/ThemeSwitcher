package com.github.dhosseiny.themeshitcher.plugin.data.helpers

import com.github.dhosseiny.themeshitcher.plugin.data.helpers.wrappers.AndroidExtensionWrapper
import org.gradle.api.Project

class AndroidProjectHelper(val project: Project) {
    val androidExtension: AndroidExtensionWrapper by lazy {
        AndroidExtensionWrapper(project.extensions.getByName("android"))
    }
}