package br.com.baz.exceptions

import io.grpc.Status

class AlreadyExistsException(
    private val productName: String
) : BaseBusinessException() {
    override fun errorMessage(): String {
        return "Produto $productName ja foi cadastrado no sistema."
    }

    override fun statusCode(): Status.Code {
        return Status.Code.ALREADY_EXISTS
    }
}