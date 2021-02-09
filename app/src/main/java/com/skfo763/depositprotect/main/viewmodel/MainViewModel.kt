package com.skfo763.depositprotect.main.viewmodel

import android.view.KeyEvent
import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import autodispose2.AutoDispose.autoDisposable
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.skfo763.base.BaseViewModel
import com.skfo763.base.extension.bindToLiveData
import com.skfo763.base.extension.logException
import com.skfo763.base.extension.plusAssign
import com.skfo763.base.theme.ThemeType
import com.skfo763.component.bottomsheetdialog.MultiSelectDialog
import com.skfo763.component.bottomsheetdialog.getFlatWhiteDialogItem
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.repository.IMainRepository
import com.skfo763.repository.data.Product
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel @ViewModelInject constructor(
    private val repository: IMainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<MainActivityUseCase>() {

    val compositeDisposable = CompositeDisposable()

    private val _bankInputText = MutableLiveData("")
    private val _productList = MutableLiveData<PagingData<Product>>()
    private val _currentUiTheme = MutableLiveData(ThemeType.DEFAULT_MODE)

    val bankInputText: LiveData<String> = _bankInputText
    val productEditText = MutableLiveData("")
    val productList: LiveData<PagingData<Product>> = _productList
    val currentUiTheme: LiveData<ThemeType> = _currentUiTheme

    val navigationViewModel by lazy { NavigationViewModel(compositeDisposable, useCase, repository, _currentUiTheme) }

    val onBankInputClicked: (View) -> Unit = {
        compositeDisposable += repository.getShortcutBankStream().map {
            it.map { item -> getFlatWhiteDialogItem(item.bankId, item.name, item.iconUrl) }
        }.subscribeOn(AndroidSchedulers.mainThread()).subscribe({
            useCase.openBankSelectDialog(it)
        }) {
            logException(it)
        }
    }

    val onBankItemClicked: (MultiSelectDialog.Item) -> Unit = {
        getProductDataStream(it.id)
        _bankInputText.value = it.title
    }

    val onProductEditTextKeyDown: (View, Int, KeyEvent?) -> Boolean = lambda@{ _, keyCode, keyEvent ->
        val eventNotNull = keyEvent ?: return@lambda false
        return@lambda if(eventNotNull.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            getProductDataStream(_bankInputText.value, productEditText.value)
            true
        } else {
            false
        }
    }

    fun getProductDataStream(
        bankName: String? = null,
        productName: String? = null
    ) {
        compositeDisposable += repository.getProductInfoStream(bankName = bankName, productName = productName)
            .cachedIn(viewModelScope)
            .bindToLiveData(_productList)
    }

    fun initializeNaviDrawer() = navigationViewModel.apply {
        getAppBaseInfo()
        getDataProviderInfo()
        getOpenSourceLicense()
    }

    fun saveThemeState(themeType: ThemeType) {
        compositeDisposable += repository.setThemeState(themeType).bindToLiveData(_currentUiTheme)
    }

}