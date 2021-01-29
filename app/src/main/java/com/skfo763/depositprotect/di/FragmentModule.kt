package com.skfo763.depositprotect.di

import com.skfo763.depositprotect.main.adapter.ProductAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    companion object {
        @Provides
        fun provideProductAdapter() = ProductAdapter.create(true)
    }

}