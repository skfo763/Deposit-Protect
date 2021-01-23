package com.skfo763.depositprotect.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.base.theme.ThemeType
import com.skfo763.depositprotect.BuildConfig
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase

class NavigationViewModel (
    useCase: MainActivityUseCase
) {

    private val _versionName = MutableLiveData(BuildConfig.VERSION_NAME)
    private val _currentUiTheme = MutableLiveData(ThemeType.DEFAULT_MODE)

    val versionName: LiveData<String> = _versionName
    val currentUiTheme: LiveData<ThemeType> = _currentUiTheme

    val appVersionInfoClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("앱 버전")
    }

    val appDetailInfoClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("스토어 페이지")
    }

    val appShareButtonClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("앱 공유")
    }

    val appThemeClicked: (String?) -> Unit = {

    }

    val openSourceLicenseClicked: (String?) -> Unit = {

    }

    val dataProviderClicked: (String?) -> Unit = {

    }

}