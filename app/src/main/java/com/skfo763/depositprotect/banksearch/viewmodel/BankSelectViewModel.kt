package com.skfo763.depositprotect.banksearch.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.skfo763.base.BaseActivityUseCase
import com.skfo763.base.BaseViewModel
import com.skfo763.repository.IBankSelectRepository

class BankSelectViewModel @ViewModelInject constructor(
    private val repository: IBankSelectRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<BaseActivityUseCase>() {




}