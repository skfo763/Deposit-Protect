package com.skfo763.repository.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.rxjava3.RxPagingSource
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.remote.data.BankData
import com.skfo763.repository.data.BankMainData
import com.skfo763.repository.data.BankType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single

@OptIn(ExperimentalPagingApi::class)
class BankInfoPagingSource(
    private val backendSource: IBankInfoApi,
    private val query: Query
): RxPagingSource<Int, BankMainData>() {

    data class Query(
        val bankType: BankType?,
        val bankName: String?
    )

    override fun loadSingle(
        params: LoadParams<Int>
    ): Single<LoadResult<Int, BankMainData>> {
        return backendSource.pageBankInfo(
                startIndex = params.key ?: 0,
                limit = params.loadSize,
                bankType = query.bankType?.type,
                bankName = query.bankName
            ).subscribeOn(AndroidSchedulers.mainThread())
            .map { toLoadResult(it) }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        response: List<BankData>
    ): LoadResult<Int, BankMainData> = LoadResult.Page(
        response.map { BankMainData(it) },
        null,
        response.lastOrNull()?.index?.plus(1) ?: 0
    )

}