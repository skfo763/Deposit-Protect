package com.skfo763.depositprotect.banksearch.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.skfo763.base.BaseActivity
import com.skfo763.base.BaseActivityUseCase
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.admob.AdMobManager
import com.skfo763.depositprotect.databinding.ActivityBankSelectBinding
import com.skfo763.depositprotect.banksearch.viewmodel.BankSelectViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BankSelectActivity(
    override val layoutResId: Int = R.layout.activity_bank_select,
    override val navHostResId: Int? = null
): BaseActivity<ActivityBankSelectBinding, BankSelectViewModel, BaseActivityUseCase>() {

    override val viewModel: BankSelectViewModel by viewModels()
    override var useCase: BaseActivityUseCase = BaseActivityUseCase(this)
    @Inject lateinit var adMobManager: AdMobManager

    override val bindingVariable: (ActivityBankSelectBinding) -> Unit = {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adMobManager.showNativeAd()

    }
}