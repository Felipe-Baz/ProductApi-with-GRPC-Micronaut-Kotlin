package br.com.baz.utils

import br.com.baz.domain.Product
import br.com.baz.dto.ProductReq
import br.com.baz.dto.ProductRes

fun Product.toProductRes(): ProductRes {
    return ProductRes(
        id = id!!,
        name = name,
        price = price,
        quantity_in_stock = quantity_in_stock
    )
}

fun ProductReq.toDomain(): Product {
    return Product(
        id = null,
        name = name,
        price = price,
        quantity_in_stock = quantity_in_stock
    )
}