package com.tomexploring.product

import org.junit.Assert.assertNotEquals
import org.junit.Test

class ProductTests {

    /**
     * Testing the copy method on the Product.
     * This is key to ensuring that items can be added to baskets and discounted correctly.
     */
    @Test
    fun product_copy() {
        val product = Product().apply {
            this.name = "Product 1"
            this.cost = 10.0
            this.stock = 5
        }
        val productCopy = product.copy()
        assertNotEquals(product, productCopy)
    }
}