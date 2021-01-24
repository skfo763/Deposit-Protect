package com.skfo763.base.extension

import androidx.lifecycle.MutableLiveData

var <T> MutableLiveData<T>.safeValue
    get() = this.value
    set(value) {
        try {
            this.value = value
        } catch (e: IllegalStateException) {
            this.postValue(value)
        } catch (e: Exception) {
            this.postValue(value)
            logException(e)
        }
    }