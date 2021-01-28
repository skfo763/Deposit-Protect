package com.skfo763.repository.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.rxjava3.RxPagingSource
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.remote.base.CompanyApiResult
import com.skfo763.remote.data.CompanyInfo
import com.skfo763.repository.data.BankData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@OptIn(ExperimentalPagingApi::class)
class BankInfoPagingSource(
    private val backendSource: IDepositProtectApi,
    private val query: Query,
): RxPagingSource<Int, BankData>() {

    data class Query(
        val bankName: String?
    )

    override fun loadSingle(
        params: LoadParams<Int>
    ): Single<LoadResult<Int, BankData>> {
        return backendSource.getCompanyList(pageNo = params.key ?: 1, companyName = query.bankName)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it) }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        response: CompanyApiResult<CompanyInfo>
    ): LoadResult<Int, BankData> = LoadResult.Page(
        response.result.item.map { BankData(it.companyId, it.name) },
        null,
        response.result.currentPageNo + 1
    )

}