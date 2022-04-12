package br.com.baz.services.impl

import br.com.baz.domain.Product
import br.com.baz.dto.ProductReq
import br.com.baz.exceptions.AlreadyExistsException
import br.com.baz.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productService = ProductServiceImpl(productRepository)

    @Test
    fun `when create method is call with valid data a ProductRes is returned`() {
        val productInput = Product(
            id = null,
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )
        val productOutput = Product(
            id = 1,
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )

        `when`(productRepository.save(productInput))
            .thenReturn(productOutput)
        val productReq = ProductReq(
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )
        val productRes = productService.create(productReq)

        Assertions.assertEquals(productRes.name, productReq.name)
        Assertions.assertEquals(productRes.price, productReq.price)
        Assertions.assertEquals(productRes.quantity_in_stock, productReq.quantity_in_stock)
    }

    @Test
    fun `when create method is call with duplicated product name, throw AlreadyExistsException`() {
        val productInput = Product(
            id = null,
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )
        val productOutput = Product(
            id = 1,
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )

        `when`(productRepository.findByNameIgnoreCase(productInput.name))
            .thenReturn(productOutput)
        val productReq = ProductReq(
            name = "product_name",
            price = 10.0,
            quantity_in_stock = 6
        )

        Assertions.assertThrowsExactly(AlreadyExistsException::class.java) {
            productService.create(productReq)
        }
    }
}