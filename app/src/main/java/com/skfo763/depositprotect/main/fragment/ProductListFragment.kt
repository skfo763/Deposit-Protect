package com.skfo763.depositprotect.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import autodispose2.AutoDispose.autoDisposable
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.skfo763.base.BaseFragment
import com.skfo763.base.extension.logException
import com.skfo763.base.extension.plusAssign
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.databinding.FragmentProductListBinding
import com.skfo763.depositprotect.main.adapter.ProductAdapter
import com.skfo763.depositprotect.main.usecase.MainActivityUseCase
import com.skfo763.depositprotect.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding, MainViewModel, MainActivityUseCase>() {

    override val layoutResId: Int = R.layout.fragment_product_list

    override val parentViewModel: MainViewModel by activityViewModels()

    @Inject lateinit var productAdapter: ProductAdapter

    override val bindingVariable: (FragmentProductListBinding) -> Unit = {
        it.parentViewModel = parentViewModel
        it.onFloatingButtonClickListener = floatingButtonClickListener
        it.productListList.layoutManager = LinearLayoutManager(context)
        it.productListList.adapter = productAdapter
    }

    override val useCase: MainActivityUseCase by lazy { parentViewModel.useCase }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentViewModel.compositeDisposable += productListDisposable()
    }

    private val floatingButtonClickListener = View.OnClickListener {
        binding.productListList.smoothScrollToPosition(0)
    }

    private fun productListDisposable(): Disposable = parentViewModel.productDataStream
        .to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
        .subscribe({
            productAdapter.submitData(lifecycle, it)
        }) {
            logException(it)
        }
}