/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcherplugin.plugin

import java.io.File

///**
// * Returns list of assets path relative to assets root directory.
// *
// * ex. somedir/assets/dir1/dir2/asset returns dir1/dir2/asset
// */
//fun FileCollection.listAssets(project: Project): List<String> = files.flatMap { rootAssetDir ->
//    project.files(rootAssetDir).asFileTree.files
//        .map { it.toRelativeString(rootAssetDir) }
//}

fun File.xmlFilesInDir(): List<File> {
    return walk()
        .maxDepth(1)
        .filter { !it.isDirectory && it.extension == XML }
        .toList()
}


const val XML = "xml"
