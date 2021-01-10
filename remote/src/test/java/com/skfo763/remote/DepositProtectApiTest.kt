package com.skfo763.remote

import com.skfo763.remote.api.IDepositProtectApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DepositProtectApiTest {

    private lateinit var networkManager: NetworkManager
    private lateinit var api: IDepositProtectApi

    @Before
    fun initialize() {
        networkManager = NetworkManager()
        api = networkManager.getOpenApiRetrofit().create(IDepositProtectApi::class.java)
    }

    @Test
    fun getCompanyList() {
        api.getCompanyList().subscribe({
            println(it.toString())
        }) {
            it.printStackTrace()
        }
    }

    @Test
    fun getProductList() {
        api.getProductList().subscribe({
            println(it.toString())
        }) {
            it.printStackTrace()
        }
    }

}