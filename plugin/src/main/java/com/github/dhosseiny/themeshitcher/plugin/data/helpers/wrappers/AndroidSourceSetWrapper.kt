package com.github.dhosseiny.themeshitcher.plugin.data.helpers.wrappers

class AndroidSourceSetWrapper(private val androidSourceSet: Any) {

    private val clazz = androidSourceSet.javaClass

    //    private val getResMethod by lazy { clazz.getMethod("getRes") }
    private val getAssetsMethod by lazy { clazz.getMethod("getAssets") }
    private val getNameMethod by lazy { clazz.getMethod("getName") }

    //    fun getRes(): AndroidSourceDirectorySetWrapper {
    //        return AndroidSourceDirectorySetWrapper(getResMethod.invoke(androidSourceSet))
    //    }

    fun getAssets(): AndroidSourceDirectorySetWrapper {
        return AndroidSourceDirectorySetWrapper(getAssetsMethod.invoke(androidSourceSet))
    }

    fun getName(): String {
        return getNameMethod.invoke(androidSourceSet) as String
    }
}