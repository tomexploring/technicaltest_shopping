package com.tomexploring.technicaltest_shopping.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomexploring.product.Product

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    val product = Product().apply {
        this.name = "Product Name"
        this.category = "Category"
        this.cost = 10.00
    }

    ProductCard(product = product, onClick = {})
}

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: Product, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .padding(all = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = product.name ?: "")
            Text(text = product.category ?: "")
            Text(text = product.getCostString())
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onClick,
            modifier = modifier
        ) {
            Text(text = "Add")
        }
    }
}
