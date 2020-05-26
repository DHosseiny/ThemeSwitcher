/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcherplugin.plugin.exceptions

class RepetitiveColorFileException(fileName: String, repeatCount: Int) :
    IllegalStateException(
        "$fileName is repeated $repeatCount times in individual places.\n" +
                "Choose one to remain and delete others")