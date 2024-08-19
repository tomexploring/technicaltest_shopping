package com.tomexploring.product

import org.junit.Test

class DiscountTests {

    /**
     * Testing that the [DiscountType.basket] discount is applied correctly
     */
    @Test
    fun discount_basket() {
        val product1 = Product().apply {
            this.id = "1"
            this.name = "Product 1"
            this.cost = 10.0
            this.stock = 5
        }

        val product2 = Product().apply {
            this.id = "2"
            this.name = "Product 1"
            this.cost = 10.0
            this.stock = 5
        }

        val product3 = Product().apply {
            this.id = "3"
            this.name = "Product 1"
            this.cost = 10.0
            this.stock = 5
        }

        val basket = Basket()
        basket.addProduct(product1)
        basket.addProduct(product2)
        basket.addProduct(product3)

        val discount = Discount().apply {
            this.percentageDiscount = 0.1f
            this.discountType = DiscountType.basket
        }

        basket.addDiscount(discount)

        assert(basket.getAppliedDiscounts().size == 1)
    }
}