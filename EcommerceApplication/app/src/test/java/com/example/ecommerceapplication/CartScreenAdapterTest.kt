package com.example.ecommerceapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceapplication.data.Product
import com.example.ecommerceapplication.data.Rating
import com.example.ecommerceapplication.recyclerView.CartScreenAdapter
import com.example.ecommerceapplication.recyclerView.CartScreenViewHolder
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CartScreenAdapterTest {

    @Mock
    private lateinit var productList: MutableList<Product>

    @Mock
    private lateinit var mockViewHolder: CartScreenViewHolder

    @Mock
    private lateinit var mockParent: ViewGroup
    @Mock
    private lateinit var mockView: View

    private lateinit var adapter: CartScreenAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        //adapter = CartScreenAdapter(productList)
    }

    @Test
    fun testOnCreateViewHolder() {
        val mockInflater = Mockito.mock(LayoutInflater::class.java)
        Mockito.`when`(mockInflater.inflate(R.layout.checkout_list, mockParent, false)).thenReturn(mockView)

        val mockContext = Mockito.mock(android.content.Context::class.java)
        Mockito.`when`(mockParent.context).thenReturn(mockContext)
        Mockito.`when`(mockContext.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)).thenReturn(mockInflater)

        val viewHolder = adapter.onCreateViewHolder(mockParent, 0)

        assertEquals(CartScreenViewHolder::class.java, viewHolder.javaClass)
    }

    @Test
    fun testOnBindViewHolder() {
        // Mock product data
        val mockRating = Rating(
            rate = 4.5,
            count = 100
        )

        val mockProduct = Product(
            id = 1,
            title = "Sample Product",
            price = 19.99,
            description = "Sample description",
            category = "Sample category",
            image = "sample_image_url",
            rating = mockRating
        )
        val position = 0
        Mockito.`when`(productList[position]).thenReturn(mockProduct)

        adapter.onBindViewHolder(mockViewHolder, position)

        Mockito.verify(mockViewHolder).bind(mockProduct)
    }

    @Test
    fun testGetItemCount() {
        Mockito.`when`(productList.size).thenReturn(3)

        val itemCount = adapter.itemCount

        assertEquals(3, itemCount)
    }
}
