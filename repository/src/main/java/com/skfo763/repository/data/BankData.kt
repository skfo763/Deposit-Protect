package com.skfo763.repository.data

import com.skfo763.remote.data.CompanyInfo

fun getTestableFamousBank(count: Int)= mutableListOf<BankData>().apply {
    for(i in 0..count) {
        add(BankData(i, "우리은행", "http://www.pressm.kr/news/photo/202101/35837_23309_2236.jpg"))
    }
}

class BankData(
    id: Int = 0,
    name: String = "",
    val url: String = ""
): CompanyInfo(id, name)