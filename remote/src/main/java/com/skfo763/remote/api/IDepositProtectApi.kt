package com.skfo763.remote.api

import com.skfo763.remote.BuildConfig
import com.skfo763.remote.base.CompanyApiResult
import com.skfo763.remote.base.ProductApiResult
import com.skfo763.remote.data.CompanyInfo
import com.skfo763.remote.data.ProductInfo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IDepositProtectApi {

    companion object {
        const val COMPANY_API_PATH = "getCompanyList202008"
        const val PRODUCTION_API_PATH = "getProductList202008"
    }

    @GET(COMPANY_API_PATH)
    fun getCompanyList(
        @Query("numOfRows") count: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("resultType") resultType: String = "json",
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.OPEN_API_KEY,
        @Query("fnclsNm", encoded = true) companyName: String? = null,
        @Query("regnNm", encoded = true) bankingZoneName: String? = null
    ): Single<CompanyApiResult<CompanyInfo>>

    @GET(PRODUCTION_API_PATH)
    fun getProductList(
        @Query("numOfRows") count: Int = 10,
        @Query("pageNo") pageNo: Int = 1,
        @Query("resultType") resultType: String = "json",
        @Query("serviceKey", encoded = true) serviceKey: String = BuildConfig.OPEN_API_KEY,
        @Query("fnclsNm", encoded = true) companyName: String? = null,
        @Query("regnNm", encoded = true) bankingZoneName: String? = null,
        @Query("prdNm", encoded = true) productName: String? = null
    ): Single<ProductApiResult<ProductInfo>>


}