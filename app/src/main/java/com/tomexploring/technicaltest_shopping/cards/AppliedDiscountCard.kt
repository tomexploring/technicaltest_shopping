package com.tomexploring.technicaltest_shopping.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomexploring.product.Discount
import com.tomexploring.product.Product

@Preview(showBackground = true)
@Composable
private fun AppliedDiscountCardPreview() {
    val product = Product().apply {
        this.name = "Product Name 1"
        this.category = "Category"
        this.cost = 10.00
    }
    val product2 = Product().apply {
        this.name = "Product Name 2"
        this.category = "Category"
        this.cost = 10.00
    }
    val product3 = Product().apply {
        this.name = "Product Name 3"
        this.category = "Category"
        this.cost = 10.00
    }

    val discount = Discount().apply {
        this.name = "Discount"
    }

    val appliedDiscount = Triple(discount, listOf(product, product2, product3), 10.00)

    AppliedDiscountCard(appliedDiscount = appliedDiscount)
}

@Composable
fun AppliedDiscountCard(
    modifier: Modifier = Modifier,
    appliedDiscount: Triple<Discount, List<Product>, Double>
) {
    Column {
        Text(text = appliedDiscount.first.name ?: "")
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            appliedDiscount.second.forEach { Text(text = it.name ?: "") }
        }
    }
}