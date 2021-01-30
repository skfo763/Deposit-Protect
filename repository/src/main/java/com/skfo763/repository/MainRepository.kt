package com.skfo763.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.skfo763.base.theme.ThemeType
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.repository.data.*
import com.skfo763.repository.pagingsource.BankInfoPagingSource
import com.skfo763.repository.pagingsource.ProductPagingSource
import com.skfo763.storage.AppDataStore
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ActivityScoped
class MainRepository @Inject constructor(
    private val depositProtectApi: IDepositProtectApi,
    private val appBaseInfoApi: IAppBaseInfoApi,
    private val bankInfoApi: IBankInfoApi,
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

    override fun getShortcutBankStream(): Single<List<BankShortcutIcon>> {
        return bankInfoApi.shortcutBankInfo.map {
            it.map { item -> BankShortcutIcon(item) }
        }
    }

    override fun getBankPageStream(pageSize: Int, bankType: BankType?, bankName: String?) = Pager(
        PagingConfig(pageSize),
        null,
        { return@Pager BankInfoPagingSource(bankInfoApi, BankInfoPagingSource.Query(bankType, bankName)) }
    ).flowable

    @ExperimentalCoroutinesApi
    override fun setThemeState(themeType: ThemeType): Single<ThemeType> {
        return appDataStore.updateCurrUiTheme(themeType).map { return@map themeType  }
    }

    override fun getProductInfoStream(pageSize: Int, bankName: String?, productName: String?) = Pager(
        PagingConfig(pageSize),
        null,
        { return@Pager ProductPagingSource(depositProtectApi, ProductPagingSource.Query(bankName, productName)) }
    ).flowable
}