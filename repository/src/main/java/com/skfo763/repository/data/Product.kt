package com.skfo763.repository.data

data class Product(
    val count: Int,
    val saleEndDate: String,
    val companyName: String,
    val registerDate: String,
    val productName: String
)

fun getTestableProduct(count: Int) = Product(
    count,
    "",
    "하나은행",
    "20190104",
    "든든플러스 적금 ${count+1}"
)