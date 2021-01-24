package com.skfo763.repository

import com.skfo763.base.theme.ThemeType
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.impl.AppBaseInfoApi
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.repository.data.*
import com.skfo763.storage.AppDataStore
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val depositProtectApi: IDepositProtectApi,
    private val appBaseInfoApi: IAppBaseInfoApi,
    private val appDataStore: AppDataStore
): IMainRepository {

    override fun getBaseAppInfo(): Single<AppInfo> {
        return appBaseInfoApi.appInfo.map { AppInfo(it.appId, it.storeUrl, it.versionCode) }
    }

    override fun getDataProviderInfo(): Single<DataProvider> {
        return appBaseInfoApi.dataProviderInfo.map { DataProvider(it.name, it.url, it.version) }
    }

    override fun getOpenSourceLicense(): Single<OpenSourceLicense> {
        return appBaseInfoApi.openSourceLicense
    }


    override fun getFamousBankList(): Observable<List<FamousBank>> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableFamousBank(10) }
    }

    override fun getBankList(pageNo: Int, count: Int) {

    }

    override fun getProductFromBankName(bankName: String?): Observable<List<Product>> {
        return Observable.timer(300, TimeUnit.MILLISECONDS).map { getTestableProduct(20) }
    }

    @ExperimentalCoroutinesApi
    override fun setThemeState(themeType: ThemeType): Single<ThemeType> {
        return appDataStore.updateCurrUiTheme(themeType).map { return@map themeType  }
    }


}