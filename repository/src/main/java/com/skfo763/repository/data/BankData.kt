package com.skfo763.repository.data

import com.skfo763.remote.data.BankData
import com.skfo763.remote.data.BankShortcut

enum class BankType(val type: String) {
    BANK("은행"),
    INVEST("금융투자"),
    COMP("종금"),
    LIFE_INS("생명보험"),
    NON_LIFE_INS("손해보험"),
    SAVING("저축은행")
}

data class BankShortcutIcon(
    val index: Int,
    val bankId: String,
    val name: String,
    val iconUrl: String
) {
    constructor(bank: BankShortcut): this(
        bank.index,
        bank.bankId,
        bank.shortcutBankName,
        bank.iconUrl
    )
}

data class BankMainData(
    val index: Int,
    val name: String,
    val type: String,
    val telephone: String,
    val url: String
) {
    constructor(bank: BankData): this(
        bank.index,
        bank.bankName,
        bank.bankType,
        bank.telephone,
        bank.url
    )
}