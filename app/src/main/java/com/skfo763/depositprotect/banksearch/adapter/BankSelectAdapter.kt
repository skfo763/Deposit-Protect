package com.skfo763.depositprotect.banksearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.skfo763.base.BaseViewHolder
import com.skfo763.depositprotect.databinding.HolderBankItemBinding
import com.skfo763.depositprotect.main.adapter.LoadingStateAdapter
import com.skfo763.repository.data.BankMainData
import com.skfo763.repository.data.BankType

class BankSelectAdapter private constructor(
    diffCallback: DiffUtil.ItemCallback<BankMainData>
): PagingDataAdapter<BankMainData, BankSelectAdapter.BankViewHolder>(diffCallback) {

    companion object {
        @JvmStatic
        fun create(attachLoadingView: Boolean) = BankSelectAdapter(BankComparator()).apply {
            if(attachLoadingView) {
                withLoadStateHeaderAndFooter(LoadingStateAdapter(), LoadingStateAdapter())
            }
        }
    }

    private val bankTypeList = listOf(
        BankType.BANK,
        BankType.INVEST,
        BankType.LIFE_INS,
        BankType.NON_LIFE_INS,
        BankType.SAVING,
        BankType.COMP
    )

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        return BankViewHolder(parent)
    }

    class BankViewHolder(parent: ViewGroup): BaseViewHolder<BankMainData?, HolderBankItemBinding>(
        HolderBankItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {

        override fun bindData(data: BankMainData?) = data?.run {

        } ?: kotlin.run {

        }
    }

    class BankComparator: DiffUtil.ItemCallback<BankMainData>() {
        override fun areItemsTheSame(oldItem: BankMainData, newItem: BankMainData) = oldItem.hashCode() == newItem.hashCode()
        override fun areContentsTheSame(oldItem: BankMainData, newItem: BankMainData) = oldItem == newItem
    }
}