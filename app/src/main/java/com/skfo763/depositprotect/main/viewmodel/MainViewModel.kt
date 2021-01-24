package com.skfo763.depositprotect.main.viewmodel

import android.view.KeyEvent
import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
import com.skfo763.repository.data.getTestableProduct
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class MainViewModel @ViewModelInject constructor(
    private val repository: IMainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<MainActivityUseCase>() {

    val compositeDisposable = CompositeDisposable()

    private val _bankInputText = MutableLiveData("")
    private val _productList = MutableLiveData<List<Product>>()
    private val _currentUiTheme = MutableLiveData(ThemeType.DEFAULT_MODE)

    val bankInputText: LiveData<String> = _bankInputText
    val productEditText = MutableLiveData("")
    val productList: LiveData<List<Product>> = _productList
    val currentUiTheme: LiveData<ThemeType> = _currentUiTheme

    val navigationViewModel by lazy { NavigationViewModel(compositeDisposable, useCase, repository, _currentUiTheme) }

    val onBankInputClicked: (View) -> Unit = {
        compositeDisposable += repository.getFamousBankList().map {
            it.map { item -> getFlatWhiteDialogItem(item.name, item.iconUrl) }
        }.observeOn(AndroidSchedulers.mainThread()).subscribe {
            useCase.openBankSelectDialog(it)
        }
    }

    val onBankItemClicked: (MultiSelectDialog.Item) -> Unit = {
        _bankInputText.value = it.title
    }

    val onProductEditTextKeyDown: (View, Int, KeyEvent?) -> Boolean = lambda@{ _, keyCode, keyEvent ->
        val eventNotNull = keyEvent ?: return@lambda false
        return@lambda if(eventNotNull.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            getProductListFromText(_bankInputText.value, productEditText.value)
            true
        } else {
            false
        }
    }

    private fun getProductListFromText(bank: String?, product: String?) {
        useCase.snackBar("$bank, $product")
    }

    fun setProductList() {
        compositeDisposable += repository.getProductFromBankName().bindToLiveData(_productList)
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