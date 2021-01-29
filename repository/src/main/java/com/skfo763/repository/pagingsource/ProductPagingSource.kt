package com.skfo763.repository.pagingsource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.rxjava3.RxPagingSource
import com.skfo763.remote.api.IDepositProtectApi
import com.skfo763.remote.base.ProductApiResult
import com.skfo763.remote.data.ProductInfo
import com.skfo763.repository.data.Product
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@OptIn(ExperimentalPagingApi::class)
class ProductPagingSource(
    private val backendSource: IDepositProtectApi,
    private val query: Query
): RxPagingSource<Int, Product>() {

    data class Query(
        val bankName: String?,
        val productName: String?
    )

    override fun loadSingle(
        params: LoadParams<Int>
    ): Single<LoadResult<Int, Product>> {
        return backendSource.getProductList(
                pageNo = params.key ?: 1,
                companyName = query.bankName,
                productName = query.productName
            ).subscribeOn(Schedulers.io())
            .map { toLoadResult(it) }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        response: ProductApiResult<ProductInfo>
    ): LoadResult<Int, Product> = LoadResult.Page(
        response.result.item.map { Product(it) },
        null,
        response.result.currentPageNo + 1
    )
}