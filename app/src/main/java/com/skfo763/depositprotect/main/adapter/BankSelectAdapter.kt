package com.skfo763.depositprotect.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.skfo763.base.BaseViewHolder
import com.skfo763.depositprotect.databinding.HolderBankItemBinding
import com.skfo763.depositprotect.databinding.HolderBankTypeBinding
import com.skfo763.repository.data.BankMainData
import com.skfo763.repository.data.BankType

class BankSelectAdapter private constructor(
    diffCallback: DiffUtil.ItemCallback<BankMainData>
): PagingDataAdapter<BankMainData, BankSelectAdapter.ViewHolder<*, out ViewDataBinding>>(diffCallback) {

    enum class ViewType(val type: Int) {
        BANK_TYPE(1),
        BANK_ITEM(2)
    }

    companion object {
        @JvmStatic
        fun create(attachLoadingView: Boolean) = BankSelectAdapter(BankComparator()).apply {
            if(attachLoadingView) {
                withLoadStateHeaderAndFooter(LoadingStateAdapter(), LoadingStateAdapter())
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder<*, out ViewDataBinding>, position: Int) {
        when(holder) {
            is ViewHolder.TypeViewHolder -> {

            }
            is ViewHolder.BankViewHolder -> {
                holder.bindData(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*, out ViewDataBinding> = when(viewType) {
        ViewType.BANK_TYPE.type -> ViewHolder.TypeViewHolder(parent)
        ViewType.BANK_ITEM.type -> ViewHolder.BankViewHolder(parent)
        else -> throw IllegalStateException("wrong view type")
    }

    sealed class ViewHolder<T, B: ViewDataBinding>(binding: B): BaseViewHolder<T, B>(binding) {
        class TypeViewHolder(parent: ViewGroup): ViewHolder<BankType?, HolderBankTypeBinding>(
            HolderBankTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) {
            override fun bindData(data: BankType?) = data?.run {

            } ?: kotlin.run {

            }
        }

        class BankViewHolder(parent: ViewGroup): ViewHolder<BankMainData?, HolderBankItemBinding>(
            HolderBankItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ) {

            override fun bindData(data: BankMainData?) = data?.run {

            } ?: kotlin.run {

            }
        }
    }

    class BankComparator: DiffUtil.ItemCallback<BankMainData>() {
        override fun areItemsTheSame(oldItem: BankMainData, newItem: BankMainData) = oldItem.hashCode() == newItem.hashCode()
        override fun areContentsTheSame(oldItem: BankMainData, newItem: BankMainData) = oldItem == newItem
    }
}