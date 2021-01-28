package com.skfo763.depositprotect.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.skfo763.base.BaseViewHolder
import com.skfo763.depositprotect.databinding.HolderProductLoadingBinding

class LoadingStateAdapter: LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        return LoadingStateViewHolder(parent)
    }

    class LoadingStateViewHolder(parent: ViewGroup): BaseViewHolder<LoadState, HolderProductLoadingBinding>(
        HolderProductLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ) {
        override fun bindData(data: LoadState) = when(data) {
            is LoadState.Error -> {
                binding.loadingBar.isVisible = false
            }
            is LoadState.Loading -> {
                binding.loadingBar.isVisible = true
            }
            is LoadState.NotLoading -> {
                binding.loadingBar.isVisible = false
            }
        }
    }

}



