/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.parser

import android.content.Context
import davud.hosseini.themeswitcher.core.theme.ASSETS_SUBFOLDER
import davud.hosseini.themeswitcher.core.theme.Palette
import davud.hosseini.themeswitcher.core.theme.ThemeInfo
import java.io.File
import java.io.InputStream

class ParserUtils {

    internal fun readThemeFileValues(context: Context, themeInfo: ThemeInfo): Palette {
        val inputStream: InputStream = getInputStream(context, themeInfo)

        val colorsMap = ColorsXmlParser.parse(inputStream)

        return Palette(colorsMap)
    }

    private fun getInputStream(context: Context, themeInfo: ThemeInfo): InputStream {
        //name of the file without extension is equal to theme name
        val fileName = "${themeInfo.name}.xml"

        return if (themeInfo.isAsset) {
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