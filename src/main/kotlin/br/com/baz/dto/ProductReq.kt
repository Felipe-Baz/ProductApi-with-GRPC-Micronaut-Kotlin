package br.com.baz.dto

data class ProductReq(
    val name: String,
    val price: Double,
    val quantity_in_stock: Int
)
