package com.example.ecommerceapplication

import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.data.Rating
import com.example.ecommerceapplication.viewModels.CartScreenViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartScreenViewModelTest {
    private lateinit var cartScreenViewModel: CartScreenViewModel

    @Before
    fun setup() {
        cartScreenViewModel = CartScreenViewModel()
    }

    @Test
    fun addToCart_existingProduct() {
        val product = Product(1, "Item 1", 10.0, "Description", "Category", "image.jpg", Rating(4.5, 2))
        cartScreenViewModel.addToCart(product)

        // Add the same product again
        cartScreenViewModel.addToCart(product)

        // Check if count and total price have increased
        assertEquals(2, product.count)
        assertEquals(20.0, product.totalPrice, 0.01)
    }

    @Test
    fun addToCart_newProduct() {
        val product = Product(1, "Item 1", 10.0, "Description", "Category", "image.jpg", Rating(4.5, 1))
        cartScreenViewModel.addToCart(product)

        // Check if the product is added with count 1 and correct total price
        assertEquals(1, product.count)
        assertEquals(10.0, product.totalPrice, 0.01)
    }

    @Test
    fun clearCart() {
        val product1 = Product(1, "Item 1", 10.0, "Description", "Category", "image.jpg", Rating(4.5, 2))
        val product2 = Product(2, "Item 2", 15.0, "Description", "Category", "image.jpg", Rating(4.0, 1))

        cartScreenViewModel.addToCart(product1)
        cartScreenViewModel.addToCart(product2)

        // Check if the cart is not empty
        assertEquals(2, cartScreenViewModel.selectedProducts.size)

        cartScreenViewModel.clearCart()

        // Check if the cart is empty after clearing
        assertEquals(0, cartScreenViewModel.selectedProducts.size)
    }

    @Test
    fun removeItem() {
        val product1 = Product(1, "Item 1", 10.0, "Description", "Category", "image.jpg", Rating(4.5, 2))
        val product2 = Product(2, "Item 2", 15.0, "Description", "Category", "image.jpg", Rating(4.0, 1))

        cartScreenViewModel.addToCart(product1)
        cartScreenViewModel.addToCart(product2)

        // Remove the first product
        cartScreenViewModel.removeItem(0)

        // Check if the product is removed from the cart
        assertEquals(1, cartScreenViewModel.selectedProducts.size)

        // Check if the count and total price of the remaining product is correct
        assertEquals(1, product2.count)
        assertEquals(15.0, product2.totalPrice, 0.01)
    }
}