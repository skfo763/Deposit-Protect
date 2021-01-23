package com.skfo763.repository

import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.repository.data.*
import com.skfo763.storage.AppDataStore
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val depositProtectApi: IDepositProtectApi,
    private val appDataStore: AppDataStore
): IMainRepository {

    override fun getBaseAppInfo(): Observable<BaseAppInfo> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableBaseApp() }
    }

    override fun getDataProviderInfo(): Observable<DataProviderInfo> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableDataProvider() }
    }

    override fun getFamousBankList(): Observable<List<FamousBank>> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableFamousBank(10) }
    }

    override fun getBankList(pageNo: Int, count: Int) {

    }

    override fun getProductFromBankName(bankName: String?): Observable<List<Product>> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableProduct(20) }
    }


}