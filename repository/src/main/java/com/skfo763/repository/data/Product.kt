package com.skfo763.repository.data

import com.skfo763.remote.data.ProductInfo

data class Product(
    val count: Int,
    val saleEndDate: String,
    val companyName: String,
    val registerDate: String,
    val productName: String
) {
    constructor(info: ProductInfo): this(info.count, info.saleEndDate, info.companyName, info.registerDate, info.productName)
}

fun getTestableProduct(count: Int) = mutableListOf<Product>().apply {
    for(i in 0..count) {
        add(Product(i, "", "하나은행", "20190104", "든든플러스 적금 ${i+1}"))
    }
}