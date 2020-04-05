/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.parser

import android.app.Activity
import android.content.Context
import davud.hosseini.themeswitcher.core.theme.ASSETS_SUBFOLDER
import java.io.File
import java.io.InputStream

class ParserUtils {

    internal fun readThemeFileValues(context: Activity, name: String, isAsset: Boolean): Map<String, Int> {
        val inputStream: InputStream = getInputStream(isAsset, context, name)

        return ColorsXmlParser.parse(inputStream)
    }

    private fun getInputStream(isAsset: Boolean, context: Activity, name: String): InputStream {
        //name of the file without extension is equal to theme name
        val fileName = "$name.xml"

        return if (isAsset) {
            inputStreamFromAssetsFile(context, fileName)
        } else inputStreamFromFile(context.filesDir, fileName)
    }


    private fun inputStreamFromAssetsFile(context: Context, fileName: String): InputStream {

        val file = File(ASSETS_SUBFOLDER, fileName)
        return context.assets.open(file.path)
    }

    private fun inputStreamFromFile(parent: File, fileName: String): InputStream {

        val file = File(parent, fileName)
        return file.inputStream()
    }
}