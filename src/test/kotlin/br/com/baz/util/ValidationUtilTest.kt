package br.com.baz.util

import br.com.baz.ProductServiceRequest
import br.com.baz.utils.ValidationUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidationUtilTest {

    @Test
    fun `when validate payload method is call with valid data, should not throw an exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("product test")
            .setPrice(10.0)
            .setQuantityInStock(1)
            .build()
        Assertions.assertDoesNotThrow{
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validate payload method is call with invalid product name, should throw an IllegalArgumentException` () {
        val request = ProductServiceRequest.newBuilder()
            .setName("")
            .setPrice(10.0)
            .setQuantityInStock(1)
            .build()
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validate payload method is call with invalid product price, should throw an IllegalArgumentException` () {
        val request = ProductServiceRequest.newBuilder()
            .setName("product test")
            .setPrice(-10.0)
            .setQuantityInStock(1)
            .build()
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validate payload method is call with invalid product Quantity In Stock, should throw an IllegalArgumentException` () {
        val request = ProductServiceRequest.newBuilder()
            .setName("product test")
            .setPrice(10.0)
            .setQuantityInStock(-1)
            .build()
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(request)
        }
    }

    @Test
    fun `when validate payload method is call with null payload, should throw an IllegalArgumentException` () {
        Assertions.assertThrows(IllegalArgumentException::class.java) {
            ValidationUtil.validatePayload(null)
        }
    }
}