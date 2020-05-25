/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcherplugin.plugin.data.storage

import davud.hosseini.themeswitcherplugin.plugin.data.helpers.wrappers.AndroidExtensionWrapper
import java.io.File

class VariantBuildResolvedDir(
    private val variantName: String,
    buildDir: File,
    private val androidExtensionWrapper: AndroidExtensionWrapper
) {

    val resolvedDir: File = File(buildDir, "generated/resolved/$variantName")

    init {
        addResolvedDirToSourceSets(resolvedDir)
    }


    private fun addResolvedDirToSourceSets(resolvedDir: File) {
        val variantSourceDirSets =
            androidExtensionWrapper.getSourceSets().getValue(variantName).getAssets()
        val srcDirs = variantSourceDirSets.getSrcDirs()
        variantSourceDirSets.setSrcDirs(srcDirs + resolvedDir)
    }
}
