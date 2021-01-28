package com.skfo763.depositprotect.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.skfo763.base.BaseViewHolder
import com.skfo763.base.extension.logException
import com.skfo763.depositprotect.databinding.HolderProductItemBinding
import com.skfo763.depositprotect.databinding.HolderProductLoadingBinding
import com.skfo763.repository.data.Product
import java.lang.IllegalStateException

class ProductAdapter private constructor(
    diffCallback: DiffUtil.ItemCallback<Product>
): PagingDataAdapter<Product, ProductAdapter.ViewHolder>(diffCallback) {

    companion object {
        @JvmStatic
        fun create(attachLoadingView: Boolean) = ProductAdapter(ProductComparator()).apply {
            if(attachLoadingView) {
                withLoadStateHeaderAndFooter(LoadingStateAdapter(), LoadingStateAdapter())
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    class ViewHolder(parent: ViewGroup) : BaseViewHolder<Product?, HolderProductItemBinding>(
        HolderProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        override fun bindData(data: Product?) = data?.run {
            binding.item = this
        } ?: kotlin.run {
            // TODO(product 비어있을 때 예외 처리)
            logException(IllegalStateException("null item"))
        }
    }

    class ProductComparator: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.hashCode() == newItem.hashCode()
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}