package com.tomexploring.product

import java.util.Locale

class Basket(
    var products: MutableList<Product> = mutableListOf(),
    var discounts: MutableList<Discount> = mutableListOf()
) {

    fun addProduct(product: Product) {
        products.add(product); updateAppliedDiscounts()
    }

    fun removeProduct(product: Product) {
        products.remove(product); updateAppliedDiscounts()
    }

    fun getProductCount() = products.size

    fun addDiscount(discount: Discount) {
        discounts.add(discount); updateAppliedDiscounts()
    }

    fun removeDiscount(discount: Discount) {
        discounts.remove(discount); updateAppliedDiscounts()
    }

    fun getDiscountCount() = discounts.size

    private val appliedDiscounts = mutableListOf<Triple<Discount, List<Product>, Double>>()

    fun getAppliedDiscounts() = appliedDiscounts.toList()

    fun updateAppliedDiscounts(): List<Triple<Discount, List<Product>, Double>> {
        appliedDiscounts.clear()

        var toDiscount = products.toMutableList()

        // Sort the discounts so that the are applied in the correct order.
//        discounts.sortBy { it.type?.ordinal }

        discounts.forEach { discount ->
            val discountedProducts = mutableListOf<Product>()
            var discountedTotal = 0.0

            when (discount.type) {
                Type.combination -> {
                    val toCheckDiscount =
                        arrayOf(mutableListOf(), mutableListOf(), mutableListOf<Product>())

                    toDiscount.forEach product@{ product ->
                        if (discount.productIds?.contains(product.id) == true) {
                            toCheckDiscount[discount.productIds!!.indexOf(product.id)].add(product)
                        }
                    }

                    while (toCheckDiscount.all { it.isNotEmpty() }) {
                        val rowDiscount = arrayOf(
                            toCheckDiscount[0][0],
                            toCheckDiscount[1][0],
                            toCheckDiscount[2][0]
                        )

                        toCheckDiscount[0].removeAt(0)
                        toCheckDiscount[1].removeAt(0)
                        toCheckDiscount[2].removeAt(0)

                        discountedProducts.addAll(rowDiscount)
                        discountedTotal += (rowDiscount.sumOf {
                            ((it.cost ?: 0.0))
                        } - (discount.setPrice ?: 0.0))
                    }
                }

                Type.multipurchase -> {
                    var toCheckDiscount = toDiscount.filter { product ->
                        discount.productIds?.contains(product.id) == true
                    }

                    while (toCheckDiscount.size >= 3) {
                        discountedProducts.addAll(toCheckDiscount.take(3))
                        discountedTotal += (toCheckDiscount[0].cost ?: 0.0)
                        toCheckDiscount = toCheckDiscount.drop(3).toMutableList()
                    }
                }

                Type.product -> {
                    toDiscount.forEach product@{ product ->
                        // Check to see if this product is part of the discount.
                        if (discount.productIds?.contains(product.id) == true) {
                            discountedProducts.add(product)
                            discountedTotal += (product.cost ?: 0.0) * (discount.percentageDiscount
                                ?: 1f)
                        }
                    }
                }

                Type.basket -> {
                    toDiscount.forEach product@{ product ->
                        // Discount the product, as a basket discount.
                        discountedProducts.add(product)
                        discountedTotal += (product.cost ?: 0.0) * (discount.percentageDiscount
                            ?: 1f)
                    }
                }

                null -> {
                    TODO()
                }
            }

            if (discountedProducts.isNotEmpty()) {
                appliedDiscounts.add(Triple(discount, discountedProducts, discountedTotal))
                toDiscount.removeAll(discountedProducts)
            }
        }

        return appliedDiscounts.toList()
    }

    private fun getSubTotal() = products.sumOf { it.cost ?: 0.0 }
    fun getSubTotalString() = String.format(Locale.getDefault(), "%.2f", getSubTotal())

    private fun getAppliedDiscountTotal() = appliedDiscounts.sumOf { it.third }
    fun getAppliedDiscountTotalString() =
        String.format(Locale.getDefault(), "%.2f", getAppliedDiscountTotal())

    private fun getFinalTotal() = getSubTotal() - getAppliedDiscounts().sumOf { it.third }
    fun getFinalTotalString() = String.format(Locale.getDefault(), "%.2f", getFinalTotal())
}
