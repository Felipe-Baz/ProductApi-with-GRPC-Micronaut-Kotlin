package br.com.baz.dto

data class ProductRes(
    val id: Long,
    val name: String,
    val price: Double,
    val quantity_in_stock: Int
)
