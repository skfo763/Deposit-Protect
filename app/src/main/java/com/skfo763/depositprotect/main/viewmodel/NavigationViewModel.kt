package com.skfo763.depositprotect.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.skfo763.depositprotect.BuildConfig
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase

class NavigationViewModel (
    useCase: MainActivityUseCase
) {

    private val _versionName = MutableLiveData(BuildConfig.VERSION_NAME)


    val versionName: LiveData<String> = _versionName

    val appVersionInfoClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("앱 버전")
    }

    val appDetailInfoClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("스토어 페이지")
    }

    val appShareButtonClicked: (String?) -> Unit = { menuValue ->
        useCase.snackBar("앱 공유")
    }
}