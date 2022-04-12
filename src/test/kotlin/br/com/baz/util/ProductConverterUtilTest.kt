package br.com.baz.util

import br.com.baz.domain.Product
import br.com.baz.dto.ProductReq
import br.com.baz.utils.toDomain
import br.com.baz.utils.toProductRes
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductConverterUtilTest {

    @Test
    fun `when toProductRes is call, should return a ProductRes with all data`() {
        val product = Product(
            id = 1,
            name = "Teste product",
            price = 10.0,
            quantity_in_stock = 10
        )
        val productRes = product.toProductRes()
        Assertions.assertEquals(productRes.id, product.id)
        Assertions.assertEquals(productRes.name, product.name)
        Assertions.assertEquals(productRes.price, product.price)
        Assertions.assertEquals(productRes.quantity_in_stock, product.quantity_in_stock)
    }

    @Test
    fun `when toDomain is call, should return a Product with all data`() {
        val productReq = ProductReq(
            name = "Teste product",
            price = 10.0,
            quantity_in_stock = 10
        )

        val product = productReq.toDomain()
        Assertions.assertEquals(product.id, null)
        Assertions.assertEquals(product.name, productReq.name)
        Assertions.assertEquals(product.price, productReq.price)
        Assertions.assertEquals(product.quantity_in_stock, productReq.quantity_in_stock)
    }
}