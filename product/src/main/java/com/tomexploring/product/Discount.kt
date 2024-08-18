package com.tomexploring.product

import java.nio.file.Path
import java.time.Instant

class Discount {
    var name: String? = null
    var start: Instant? = null
    var end: Instant? = null
    var type: Type? = null
    var productIds: List<String>? = null
    var percentageDiscount: Float? = null
    var setPrice: Double? = null

    fun load(vararg string: String): List<Discount> =
        DbLoader.loadItems(Array<Discount>::class.java, *string)

    fun load(vararg path: Path): List<Discount> =
        DbLoader.loadItems(Array<Discount>::class.java, *path)
}

enum class Type { multipurchase, combination, product, basket }
