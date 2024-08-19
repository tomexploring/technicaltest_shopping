package com.tomexploring.technicaltest_shopping.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tomexploring.product.Basket
import com.tomexploring.product.Discount
import com.tomexploring.product.DiscountType
import com.tomexploring.product.Product
import com.tomexploring.technicaltest_shopping.R
import com.tomexploring.technicaltest_shopping.cards.ProductCard
import com.tomexploring.technicaltest_shopping.ui.theme.Technicaltest_shoppingTheme

class MainActivity : ComponentActivity() {

    private val discounts = mutableListOf(
        Discount().apply {
            this.name = "Buy 2 Get 1 Free Coffee"
            this.discountType = DiscountType.multipurchase
            this.productIds = listOf("g010")
        },
        Discount().apply {
            this.name = "25% Off Pasta"
            this.discountType = DiscountType.product
            this.productIds = listOf("g008")
            this.percentageDiscount = 0.25f
        },
        Discount().apply {
            this.name = "Egg, Bread, Beef Combination Fiver"
            this.discountType = DiscountType.combination
            this.productIds = listOf("g003", "g004", "g005")
            this.setPrice = 5.0
        },
        Discount().apply {
            this.name = "5% Off Basket"
            this.discountType = DiscountType.basket
            this.percentageDiscount = 0.05f
        }
    )

    val basket = Basket(discounts = discounts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Technicaltest_shoppingTheme {
                ProductList(products = getTestProductList())
            }
        }
    }

    private fun getTestProductList(): List<Product> {
        val groceryJson = resources.openRawResource(R.raw.product_grocery)
            .bufferedReader().use { it.readText() }
        val techJson = resources.openRawResource(R.raw.product_tech)
            .bufferedReader().use { it.readText() }
        val stringList = arrayOf(groceryJson, techJson)
        return Product().load(*stringList)
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun Preview(modifier: Modifier = Modifier, name: String = "Android") {
        ProductList(products = getTestProductList())
    }

    @Composable
    private fun ProductList(modifier: Modifier = Modifier, products: List<Product>) {
        val basketProducts = remember { mutableStateListOf<Product>() }
        basket.products = basketProducts

        key(basketProducts) {
            Column(modifier) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(products) { product ->
                        Log.v("COMPOSE", "This get rendered ${product.name}")
                        ProductCard(product = product, onClick = {
                            basketProducts.add(product.copy())
                            basket.updateAppliedDiscounts()
                        })
                    }
                }
                BasketCard(basket)
            }
        }
    }

    @Composable
    private fun BasketCard(basket: Basket) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Basket",
                textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
            )
            Row {
                Text(text = "Items: ${basket.getProductCount()}")
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Subtotal: £${basket.getSubTotalString()}"
                )
            }
            Text(
                text = "Discounts",
                textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
            )
            Column {
                basket.getAppliedDiscounts().forEach {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = it.first.name ?: "")
                        Text(text = "Items: ${it.second.size}")
                    }
                }
                Row(horizontalArrangement = Arrangement.End) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Final: £${basket.getFinalTotalString()}")
                }
            }
        }
    }
}
