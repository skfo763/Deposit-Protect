package com.skfo763.repository.data

fun getTestableFamousBank(count: Int)= mutableListOf<FamousBank>().apply {
    for(i in 0..count) {
        add(FamousBank("우리은행", "http://www.pressm.kr/news/photo/202101/35837_23309_2236.jpg"))
    }
}

data class FamousBank(
    val name: String,
    val iconUrl: String
)