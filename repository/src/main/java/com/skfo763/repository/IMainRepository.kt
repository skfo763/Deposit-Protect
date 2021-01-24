package com.skfo763.repository

import com.skfo763.repository.data.BaseAppInfo
import com.skfo763.repository.data.DataProviderInfo
import com.skfo763.repository.data.FamousBank
import com.skfo763.repository.data.Product
import io.reactivex.rxjava3.core.Observable

interface IMainRepository {

    fun getBaseAppInfo(): Observable<BaseAppInfo>

    fun getDataProviderInfo(): Observable<DataProviderInfo>

    fun getFamousBankList(): Observable<List<FamousBank>>

    fun getBankList(pageNo: Int, count: Int)

    fun getProductFromBankName(bankName: String? = null): Observable<List<Product>>

}