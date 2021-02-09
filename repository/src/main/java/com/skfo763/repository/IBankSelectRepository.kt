package com.skfo763.repository

import androidx.paging.PagingData
import com.skfo763.repository.data.BankMainData
import com.skfo763.repository.data.BankType
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface IBankSelectRepository {

    fun getBankType(): Single<List<BankType>>

    fun getBankPageStream(pageSize: Int = 24, bankType: BankType?, bankName: String?) : Flowable<PagingData<BankMainData>>

}