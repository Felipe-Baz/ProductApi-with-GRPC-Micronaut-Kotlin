package br.com.baz.resources

import br.com.baz.ProductServiceRequest
import br.com.baz.ProductsServiceGrpc.ProductsServiceBlockingStub
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@MicronautTest
internal class ProductResourcesTestIT(
    private val productsServiceBlockingStub: ProductsServiceBlockingStub
) {
    @Test
    fun `when ProductsServiceGrpc create method is call with valid data a success is returned`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("product test")
            .setPrice(10.0)
            .setQuantityInStock(1)
            .build()
        val response = productsServiceBlockingStub.create(request)
        Assertions.assertEquals(1, response.id)
        Assertions.assertEquals(request.name, response.name)
    }
}