package com.tomexploring.product

import com.google.gson.Gson
import java.nio.file.Path
import java.util.Locale

class Product {

    var id: String = ""
    var name: String? = null
    var price: Double? = null
    var category: String? = null
    var cost: Double? = null
    var supplier: String? = null
    var stock: Int? = null

    fun getCostString() = String.format(Locale.getDefault(), "%.2f", cost ?: 0)

    fun load(vararg string: String): List<Product> =
        DbLoader.loadItems(Array<Product>::class.java, *string)

    fun load(vararg path: Path): List<Product> =
        DbLoader.loadItems(Array<Product>::class.java, *path)

    fun copy(): Product {
        val gson = Gson()
        return gson.fromJson(gson.toJson(this), Product::class.java)
    }
}
