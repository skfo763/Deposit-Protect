package com.skfo763.depositprotect.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.skfo763.base.BaseFragment
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.databinding.FragmentProductListBinding
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.depositprotect.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding, MainViewModel, MainActivityUseCase>() {

    override val layoutResId: Int = R.layout.fragment_product_list

    override val parentViewModel: MainViewModel by activityViewModels()

    override val bindingVariable: (FragmentProductListBinding) -> Unit = {
        it.parentViewModel = parentViewModel
        it.productListList.layoutManager = LinearLayoutManager(context)
    }

    override val useCase: MainActivityUseCase by lazy { parentViewModel.useCase }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentViewModel.setProductList()
    }

}