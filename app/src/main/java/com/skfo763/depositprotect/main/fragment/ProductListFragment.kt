package com.skfo763.depositprotect.main.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import com.jakewharton.rxbinding4.view.clicks
import com.skfo763.base.BaseFragment
import com.skfo763.base.extension.logException
import com.skfo763.base.extension.plusAssign
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.databinding.FragmentProductListBinding
import com.skfo763.depositprotect.main.adapter.ProductAdapter
import com.skfo763.depositprotect.main.component.FastScrollLayoutManager
import com.skfo763.depositprotect.main.component.fastScrollTo
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.depositprotect.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding, MainViewModel, MainActivityUseCase>() {

    override val layoutResId: Int = R.layout.fragment_product_list

    override val parentViewModel: MainViewModel by activityViewModels()

    @Inject lateinit var productAdapter: ProductAdapter

    private val loadStateListener: (CombinedLoadStates) -> Unit = {
        binding.progress.isVisible = it.refresh is LoadState.Loading
        binding.dataLoadError.isVisible = it.refresh is LoadState.Error
    }

    override val bindingVariable: (FragmentProductListBinding) -> Unit = {
        it.parentViewModel = parentViewModel
        it.productListList.layoutManager = FastScrollLayoutManager(requireContext())
        it.productListList.adapter = productAdapter
        productAdapter.addLoadStateListener(loadStateListener)

        observeFloatingButtonClick()
        observeProductLiveData()
    }

    override val useCase: MainActivityUseCase by lazy { parentViewModel.useCase }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentViewModel.getProductDataStream()
    }

    @SuppressLint("AutoDispose")
    private fun observeFloatingButtonClick() {
        parentViewModel.compositeDisposable += binding.goToTop.clicks()
            .throttleFirst(400, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.productListList.fastScrollTo(0, 4)
            }) {
                logException(it)
            }
    }

    private fun observeProductLiveData() = parentViewModel.productList.observe(viewLifecycleOwner, {
        productAdapter.submitData(lifecycle, it)
    })
}