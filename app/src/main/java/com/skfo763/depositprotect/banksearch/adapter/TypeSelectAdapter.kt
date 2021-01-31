package com.skfo763.depositprotect.banksearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.skfo763.base.BaseViewHolder
import com.skfo763.depositprotect.databinding.HolderBankTypeBinding
import com.skfo763.repository.data.BankType

class TypeSelectAdapter: RecyclerView.Adapter<TypeSelectAdapter.TypeViewHolder>() {

    private val _bankTypeList = mutableListOf<BankType>()

    var bankTypeList: List<BankType> = _bankTypeList
        set(value) {
            _bankTypeList.clear()
            _bankTypeList.addAll(value)
            field = value
        }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bindData(_bankTypeList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        return TypeViewHolder(parent)
    }

    override fun getItemCount() = _bankTypeList.size

    class TypeViewHolder(parent: ViewGroup): BaseViewHolder<BankType?, HolderBankTypeBinding>(
        HolderBankTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        override fun bindData(data: BankType?) = data?.run {
            binding.bankTypeText.text = this.type
            binding.bankTypeText.isVisible = true
        } ?: kotlin.run {
            binding.bankTypeText.isVisible = false
        }
    }

}