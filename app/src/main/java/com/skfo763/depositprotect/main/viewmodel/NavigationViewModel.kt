package com.skfo763.depositprotect.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.base.extension.bindToLiveData
import com.skfo763.base.extension.plusAssign
import com.skfo763.base.theme.ThemeType
import com.skfo763.depositprotect.BuildConfig
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.remote.data.OpenSourceLicense
import com.skfo763.repository.IMainRepository
import com.skfo763.repository.data.AppInfo
import com.skfo763.repository.data.DataProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

class NavigationViewModel(
    private val compositeDisposable: CompositeDisposable,
    private val useCase: MainActivityUseCase,
    private val repository: IMainRepository,
    val currentUiTheme: LiveData<ThemeType>
) {

    private val _versionName = MutableLiveData(BuildConfig.VERSION_NAME)
    private val _appInfo = MutableLiveData<AppInfo>()
    private val _dataProvider = MutableLiveData<DataProvider>()
    private val _openSourceLicenseUrl = MutableLiveData<OpenSourceLicense>()

    val versionName: LiveData<String> = _versionName
    val appInfo: LiveData<AppInfo> = _appInfo
    val dataProvider: LiveData<DataProvider> = _dataProvider
    val openSourceLicenseUrl: LiveData<OpenSourceLicense> = _openSourceLicenseUrl

    val appVersionInfoClicked: (String?) -> Unit = {
        val versionCode = appInfo.value?.versionCode ?: BuildConfig.VERSION_CODE
        if(BuildConfig.VERSION_CODE < versionCode) {
            useCase.showAppUpdateDialog(versionCode, appInfo.value?.storeUrl)
        } else {
            useCase.snackBar(R.string.is_newest_version)
        }
    }

    val appDetailInfoClicked: (String?) -> Unit = {
        useCase.openMarketUrl(appInfo.value?.storeUrl)
    }

    val appShareButtonClicked: (String?) -> Unit = {
        useCase.sendUrl(appInfo.value?.storeUrl)
    }

    val appThemeClicked: (String?) -> Unit = {
        useCase.showSelectThemeDialog(currentUiTheme.value ?: ThemeType.DEFAULT_MODE)
    }

    val openSourceLicenseClicked: (String?) -> Unit = {
        useCase.openNormalUrl(openSourceLicenseUrl.value?.url)
    }

    val dataProviderClicked: (String?) -> Unit = {
        dataProvider.value?.let {
            useCase.showDataProviderDialog(it)
        }
    }

    fun getAppBaseInfo() {
        compositeDisposable += repository.getBaseAppInfo().bindToLiveData(_appInfo)
    }

    fun getDataProviderInfo() {
        compositeDisposable += repository.getDataProviderInfo().bindToLiveData(_dataProvider)
    }

    fun getOpenSourceLicense() {
        compositeDisposable += repository.getOpenSourceLicense().bindToLiveData(_openSourceLicenseUrl)
    }
}