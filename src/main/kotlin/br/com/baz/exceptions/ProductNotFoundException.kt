package br.com.baz.exceptions

import io.grpc.Status

class ProductNotFoundException(
    private val productId: Long
) : BaseBusinessException() {
    override fun errorMessage(): String {
        return "Produto id: $productId Not Found."
    }

    override fun statusCode(): Status.Code {
        return Status.Code.NOT_FOUND
    }
}