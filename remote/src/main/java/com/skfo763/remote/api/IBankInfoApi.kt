package com.skfo763.remote.api

import androidx.annotation.VisibleForTesting
import com.skfo763.remote.data.BankData
import com.skfo763.remote.data.BankShortcut
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.core.Single

interface IBankInfoApi {

    @VisibleForTesting
    fun getMoshi(): Moshi

    val shortcutBankInfo: Single<List<BankShortcut>>

    fun pageBankInfo(startIndex: Int, limit: Int, bankType: String? = null, bankName: String? = null): Single<List<BankData>>

}