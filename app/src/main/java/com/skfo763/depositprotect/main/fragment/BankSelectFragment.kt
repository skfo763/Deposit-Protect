package com.skfo763.depositprotect.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.skfo763.base.BaseFragment
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.databinding.FragmentBankSelectBinding
import com.skfo763.depositprotect.main.adapter.BankSelectAdapter
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.depositprotect.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BankSelectFragment: BaseFragment<FragmentBankSelectBinding, MainViewModel, MainActivityUseCase>() {

    override val layoutResId: Int = R.layout.fragment_bank_select

    override val parentViewModel: MainViewModel by activityViewModels()

    @Inject lateinit var bankAdapter: BankSelectAdapter

    override val bindingVariable: (FragmentBankSelectBinding) -> Unit = {
        it.parentViewModel = parentViewModel
        it.bankSelectContent.layoutManager = GridLayoutManager(context, 2)
        it.bankSelectContent.adapter = bankAdapter
    }

    override val useCase: MainActivityUseCase by lazy { parentViewModel.useCase }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}