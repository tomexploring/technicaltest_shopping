package com.tomexploring.product

import android.util.Log
import com.google.gson.Gson
import java.io.File
import java.nio.file.Path
import kotlin.io.path.pathString


class DbLoader {
    companion object {
        fun <T> loadItems(arr: Class<Array<T>>?, vararg paths: Path): List<T> {
            Log.d(Companion::class.java.simpleName, "Loading DbItem from ${paths.size} files")

            val mutableList = mutableListOf<T>()
            paths.forEach {
                val fileContent = File(it.pathString).readText()
                val items = Gson().fromJson(fileContent, arr)
                mutableList.addAll(items)
            }

            Log.d(Companion::class.java.simpleName, "Loaded ${mutableList.size} DbItems")

            return mutableList
        }

        fun <T> loadItems(arr: Class<Array<T>>?, vararg jsonString: String): List<T> {
            Log.d(
                Companion::class.java.simpleName,
                "Loading DbItem from ${jsonString.size} strings"
            )

            val list = mutableListOf<T>()
            jsonString.forEach {
                val items = Gson().fromJson(it, arr)
                list.addAll(items)
            }

            Log.d(Companion::class.java.simpleName, "Loaded ${list.size} DbItems")

            return list
        }
    }
}
