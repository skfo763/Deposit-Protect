package com.skfo763.remote.impl

import android.accounts.NetworkErrorException
import androidx.annotation.VisibleForTesting
import com.google.firebase.firestore.FirebaseFirestore
import com.skfo763.remote.api.IAppBaseInfoApi
import com.skfo763.remote.base.toCustomDataType
import com.skfo763.remote.data.AppBaseInfo
import com.skfo763.remote.data.DataProviderInfo
import com.skfo763.remote.data.OpenSourceLicense
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.core.Single

class AppBaseInfoApi(
    private val moshi: Moshi,
    private val db: FirebaseFirestore
): IAppBaseInfoApi {

    @VisibleForTesting
    override fun getMoshi() = moshi

    override val appInfo: Single<AppBaseInfo> get() = Single.create { emitter ->
        db.collection("base")
            .document("app_info")
            .get()
            .addOnSuccessListener {
                emitter.onSuccess(it.data.toCustomDataType(moshi, AppBaseInfo::class.java))
            }.addOnFailureListener { e ->
                emitter.onError(e)
            }.addOnCanceledListener {
                emitter.onError(NetworkErrorException())
            }
    }

    override val dataProviderInfo: Single<DataProviderInfo> get() = Single.create { emitter ->
        db.collection("base")
            .document("data_provider")
            .get()
            .addOnSuccessListener {
                emitter.onSuccess(it.data.toCustomDataType(moshi, DataProviderInfo::class.java))
            }.addOnFailureListener { e ->
                emitter.onError(e)
            }.addOnCanceledListener {
                emitter.onError(NetworkErrorException())
            }
    }

    override val openSourceLicense: Single<OpenSourceLicense> get() = Single.create { emitter ->
        db.collection("base")
            .document("open_source_license")
            .get()
            .addOnSuccessListener {
                emitter.onSuccess(it.data.toCustomDataType(moshi, OpenSourceLicense::class.java))
            }.addOnFailureListener { e ->
                emitter.onError(e)
            }.addOnCanceledListener {
                emitter.onError(NetworkErrorException())
            }
    }

}

