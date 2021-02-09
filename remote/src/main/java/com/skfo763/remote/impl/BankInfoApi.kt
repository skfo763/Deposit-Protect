package com.skfo763.remote.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.skfo763.remote.api.IBankInfoApi
import com.skfo763.remote.base.toCustomListDataType
import com.skfo763.remote.data.BankData
import com.skfo763.remote.data.BankShortcut
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.core.Single

class BankInfoApi(
    private val moshi: Moshi,
    private val db: FirebaseFirestore
): IBankInfoApi {

    override fun getMoshi() = moshi

    override val shortcutBankInfo: Single<List<BankShortcut>> get() = Single.create { emitter ->
        db.collection("bank")
            .document("shortcut")
            .collection("bank_list")
            .orderBy("index")
            .get()
            .addOnSuccessListener {
                emitter.onSuccess(it.toCustomListDataType(moshi, BankShortcut::class.java, BankShortcut()))
            }.addOnFailureListener {
                emitter.onError(it)
            }
    }

    override fun pageBankInfo(startIndex: Int, limit: Int, bankType: String?, bankName: String?): Single<List<BankData>> = Single.create { emitter ->
        db.collection("bank")
            .document("all")
            .collection("bank_list")
            .apply {
                bankType?.let { this.whereEqualTo("bank_type", bankType) }
                bankName?.let { this.whereEqualTo("bank_name", bankName) }
            }.orderBy("index")
            .startAfter(startIndex)
            .limit(limit.toLong())
            .get()
            .addOnSuccessListener {
                emitter.onSuccess(it.toCustomListDataType(moshi, BankData::class.java, BankData()))
            }.addOnFailureListener {
                emitter.onError(it)
            }
    }
}