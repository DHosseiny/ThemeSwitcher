/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * Created by Davud. ThemeChanger project.
 */
@Throws(IOException::class)
fun copyFile(sourceInputStream: InputStream, destFile: File) {
    val out = FileOutputStream(destFile)
    val buf = ByteArray(4096)
    var len: Int
    while (sourceInputStream.read(buf).also { len = it } > 0) {
        out.write(buf, 0, len)
    }
    out.close()
}
