package com.skfo763.depositprotect.main.viewmodel

import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.skfo763.base.BaseViewModel
import com.skfo763.component.bottomsheetdialog.MultiSelectDialog
import com.skfo763.component.bottomsheetdialog.getTestableDialogItem
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<MainActivityUseCase>() {

    val compositeDisposable = CompositeDisposable()
    val navigationViewModel = NavigationViewModel()


    val onBankSelectBarClicked: (View) -> Unit = {
        val itemList = mutableListOf<MultiSelectDialog.Item>()
        for(i in 0..100) {
            itemList.add(getTestableDialogItem())
        }
        useCase.openBankSelectDialog(itemList)
    }

}