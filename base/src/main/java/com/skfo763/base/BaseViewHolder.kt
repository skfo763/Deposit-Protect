package com.skfo763.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.errorprone.annotations.DoNotCall

open class BaseViewHolder<T, B: ViewDataBinding>(
    val binding: B
): RecyclerView.ViewHolder(binding.root) {

    open fun bindData(data: T) = Unit

    open fun onAttachToWindow() = Unit

    open fun onDetachToWindow() = Unit

}