/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package com.github.dhosseiny.themeshitcher.plugin.exceptions

class RepetitiveColorFileException(fileName: String, repeatCount: Int) :
    IllegalStateException(
        "$fileName is repeated $repeatCount times in individual places.\n" +
                "Choose one to remain and delete others")