/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core

import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.regex.Pattern

/**
 * Created by Davud. ThemeChanger project.
 */

private val pattern = Pattern.compile("[\\-0-9]+")

fun parseInt(value: String?): Int {
    if (value == null) {
        return 0
    }
    var `val` = 0
    try {
        val matcher = pattern.matcher(value)
        if (matcher.find()) {
            val num = matcher.group(0)
            `val` = num.toInt()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return `val`
}

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
