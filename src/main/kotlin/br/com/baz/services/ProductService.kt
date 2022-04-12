package br.com.baz.services

import br.com.baz.dto.ProductReq
import br.com.baz.dto.ProductRes

interface ProductService {
    fun create(req: ProductReq): ProductRes
}