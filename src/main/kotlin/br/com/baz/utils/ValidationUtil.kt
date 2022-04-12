package br.com.baz.utils

import br.com.baz.ProductServiceRequest

class ValidationUtil {
    companion object {
        fun validatePayload(payload: ProductServiceRequest?): ProductServiceRequest {
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw IllegalArgumentException("Nome nao pode ser nulo ou vazio")

                if (it.price.isNaN() || it.price < 0)
                    throw IllegalArgumentException("Precisa ser um valor valido")

                if (it.quantityInStock < 0)
                    throw IllegalArgumentException("Precisa ser uma quantidade valida")

                return it
            }
            throw IllegalArgumentException()
        }
    }
}