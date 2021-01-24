package com.skfo763.depositprotect.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.base.theme.ThemeType
import com.skfo763.depositprotect.BuildConfig
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.repository.IMainRepository

class NavigationViewModel(
    useCase: MainActivityUseCase,
    repository: IMainRepository
) {

    private val _versionName = MutableLiveData(BuildConfig.VERSION_NAME)
    private val _currentUiTheme = MutableLiveData(ThemeType.DEFAULT_MODE)

    val versionName: LiveData<String> = _versionName
    val currentUiTheme: LiveData<ThemeType> = _currentUiTheme

    val appVersionInfoClicked: (String?) -> Unit = { menuValue ->



    }

    val appDetailInfoClicked: (String?) -> Unit = { menuValue ->



    }

    val appShareButtonClicked: (String?) -> Unit = { menuValue ->



    }

    val appThemeClicked: (String?) -> Unit = {

    }

    val openSourceLicenseClicked: (String?) -> Unit = {

    }

    val dataProviderClicked: (String?) -> Unit = {

    }

}