package com.skfo763.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.data
import androidx.datastore.rxjava3.updateDataAsync
import com.skfo763.base.theme.ThemeType
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class AppDataStore(context: Context) {

    // UI 테마 (다크/라이트/시스템)
    private val UI_THEME = stringPreferencesKey("ui_theme")

    private val currUiTheme = RxPreferenceDataStoreBuilder(context, "current_ui_theme").build()

    @ExperimentalCoroutinesApi
    val currUiThemeFlow: Flowable<ThemeType> = currUiTheme.data().map { pref ->
        return@map pref[UI_THEME]?.let { ThemeType.valueOf(it) } ?: ThemeType.DEFAULT_MODE
    }

    @ExperimentalCoroutinesApi
    val updateCurrUiTheme: Single<Preferences> = currUiTheme.updateDataAsync {
        return@updateDataAsync Single.just(it.toMutablePreferences())
    }
}