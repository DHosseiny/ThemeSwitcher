package davud.hosseini.themeswitcher.core.parser;

import android.graphics.Color.parseColor
import android.util.Xml
import davud.hosseini.themeswitcher.core.theme.ColorKey
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

object ColorsXmlParser {

    private const val RESOURCES_TAG: String = "resources"
    private const val COLOR_TAG: String = "color"
    private const val NAME_ATTR: String = "name"

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream): Map<ColorKey, Int> {
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readResources(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readResources(parser: XmlPullParser): Map<ColorKey, Int> {
        val colors = mutableMapOf<ColorKey, Int>()

        parser.require(XmlPullParser.START_TAG, null, RESOURCES_TAG)
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == COLOR_TAG) {
                val key: ColorKey = readKey(parser)
                val value: Int = readValue(parser)

                colors[key] = value
            }
        }
        return colors
    }

    private fun readKey(parser: XmlPullParser): ColorKey = ColorKey(parser.getAttributeValue(null, NAME_ATTR))

    private fun readValue(parser: XmlPullParser): Int = parseColor(parser.nextText())

}