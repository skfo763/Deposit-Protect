package com.skfo763.base.extension

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

operator fun CompositeDisposable.plusAssign(disposable: Disposable) { add(disposable) }

fun <T> Single<T>.bindToLiveData(liveData: MutableLiveData<T>): Disposable {
    return this.subscribeOn(AndroidSchedulers.mainThread())
        .subscribe({
            liveData.safeValue = it
        }) {
            logException(it)
        }
}

fun <T> Observable<T>.bindToLiveData(liveData: MutableLiveData<T>): Disposable {
    return this.subscribeOn(AndroidSchedulers.mainThread())
        .subscribe({
            liveData.safeValue = it
        }) {
            logException(it)
        }
}