package com.skfo763.depositprotect.main.usecase

import com.skfo763.base.BaseActivityUseCase
import com.skfo763.component.bottomsheetdialog.MultiSelectDialog
import com.skfo763.component.bottomsheetdialog.getTestableDialogItem
import com.skfo763.depositprotect.main.activity.MainActivity

class MainActivityUseCase(private val activity: MainActivity): BaseActivityUseCase(activity) {

    fun openBankSelectDialog(itemList: List<MultiSelectDialog.Item>) {
        MultiSelectDialog.Builder()
            .setItem(itemList)
            .build()
            .show(activity.supportFragmentManager, null)
    }

}