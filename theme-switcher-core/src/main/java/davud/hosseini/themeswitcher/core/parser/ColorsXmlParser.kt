package davud.hosseini.themeswitcher.core.parser;

import android.graphics.Color.parseColor
import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

object ColorsXmlParser {

    private const val RESOURCES_TAG: String = "resources"
    private const val COLOR_TAG: String = "color"
    private const val NAME_ATTR: String = "name"

    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream): Map<String, Int> {
        inputStream.use {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readResources(parser)
        }
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun readResources(parser: XmlPullParser): Map<String, Int> {
        val colors = mutableMapOf<String, Int>()

        parser.require(XmlPullParser.START_TAG, null, RESOURCES_TAG)
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType == XmlPullParser.START_TAG && parser.name == COLOR_TAG) {
                val key: String = readKey(parser)
                val value: Int = readValue(parser)

                colors[key] = value
            }
        }
        return colors
    }

    private fun readKey(parser: XmlPullParser): String = parser.getAttributeValue(null, NAME_ATTR)

    private fun readValue(parser: XmlPullParser): Int = parseColor(parser.nextText())

}