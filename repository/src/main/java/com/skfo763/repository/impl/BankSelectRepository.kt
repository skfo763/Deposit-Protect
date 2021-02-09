package com.skfo763.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.repository.IBankSelectRepository
import com.skfo763.repository.data.BankType
import com.skfo763.repository.pagingsource.BankInfoPagingSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BankSelectRepository @Inject constructor(
    private val bankInfoApi: IBankInfoApi
): IBankSelectRepository {

    override fun getBankType(): Single<List<BankType>> = Single.just(
        listOf(
            BankType.BANK,
            BankType.INVEST,
            BankType.LIFE_INS,
            BankType.NON_LIFE_INS,
            BankType.SAVING,
            BankType.COMP
        )
    )

    override fun getBankPageStream(pageSize: Int, bankType: BankType?, bankName: String?) = Pager(
        PagingConfig(pageSize),
        null,
        { return@Pager BankInfoPagingSource(bankInfoApi, BankInfoPagingSource.Query(bankType, bankName)) }
    ).flowable


}