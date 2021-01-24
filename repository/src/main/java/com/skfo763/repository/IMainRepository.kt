package com.skfo763.repository

import com.skfo763.base.theme.ThemeType
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.repository.data.AppInfo
import com.skfo763.repository.data.DataProvider
import com.skfo763.repository.data.FamousBank
import com.skfo763.repository.data.Product
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface IMainRepository {

    fun getBaseAppInfo(): Single<AppInfo>

    fun getDataProviderInfo(): Single<DataProvider>

    fun getOpenSourceLicense(): Single<OpenSourceLicense>

    fun getFamousBankList(): Observable<List<FamousBank>>

    fun getBankList(pageNo: Int, count: Int)

    fun getProductFromBankName(bankName: String? = null): Observable<List<Product>>

    fun setThemeState(themeType: ThemeType): Single<ThemeType>

}