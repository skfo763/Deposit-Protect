package com.skfo763.depositprotect.main.usecase

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.skfo763.base.BaseActivityUseCase
import com.skfo763.base.extension.logException
import com.skfo763.base.extension.parsedUri
import com.skfo763.base.theme.ThemeType
import com.skfo763.base.theme.applyTheme
import com.skfo763.component.bottomsheetdialog.MultiSelectDialog
import com.skfo763.component.themedialog.ThemeDialogBuilder
import com.skfo763.depositprotect.R
import com.skfo763.depositprotect.main.activity.MainActivity
import com.skfo763.repository.data.DataProvider

class MainActivityUseCase(private val activity: MainActivity): BaseActivityUseCase(activity) {

    val themeDialogItems by lazy { listOf(
        ThemeDialogBuilder.Item(activity.getString(R.string.theme_light), ThemeType.LIGHT_MODE),
        ThemeDialogBuilder.Item(activity.getString(R.string.theme_dark), ThemeType.DARK_MODE),
        ThemeDialogBuilder.Item(activity.getString(R.string.theme_system_default), ThemeType.DEFAULT_MODE)
    ) }

    fun openBankSelectDialog(itemList: List<MultiSelectDialog.Item>) {
        MultiSelectDialog.Builder()
            .setTitle("은행을 선택하세요.")
            .setItem(itemList)
            .setOnItemClickListener { dialog, item ->
                dialog.dismiss()
                activity.viewModel.onBankItemClicked(item)
            }.build()
            .show(activity.supportFragmentManager, null)
    }

    fun openMarketUrl(url: String?) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url?.parsedUri ?: run {
                Uri.parse("market://details?id=${activity.packageName}")
            })
            activity.startActivity(intent)
        } catch (e: Exception) {
            activity.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=${activity.packageName}")
                )
            )
        }
    }

    fun sendUrl(url: String?) {
        try {
            val intent = Intent(Intent.ACTION_SEND).apply {
                addCategory(Intent.CATEGORY_DEFAULT)
                putExtra(Intent.EXTRA_TEXT, url ?: "https://play.google.com/store/apps/details?id=${activity.packageName}")
                putExtra(Intent.EXTRA_TITLE, activity.getString(R.string.share_app_title))
                type = "text/plain"
            }
            logAnalyticsEvent(FirebaseAnalytics.Event.SHARE, bundleOf(FirebaseAnalytics.Param.METHOD to "url"))
            activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.share_app_message)))
        } catch (e: ActivityNotFoundException) {
            logException(e)
        }
    }

    fun showSelectThemeDialog(currentThemeType: ThemeType) {
        ThemeDialogBuilder(activity)
            .setDialogTitle(activity.getString(R.string.theme_select_dialog_title))
            .setThemeItems(currentThemeType, themeDialogItems) {
                if(it.themeType == activity.viewModel.currentUiTheme.value) {
                    return@setThemeItems
                }
                applyTheme(it.themeType)
                activity.viewModel.saveThemeState(it.themeType)
            }.create()
            .show()
    }

    fun showDataProviderDialog(value: DataProvider) {
        AlertDialog.Builder(activity)
            .setTitle(R.string.data_provider_dialog_title)
            .setMessage(value.providerName)
            .setPositiveButton(R.string.data_provider_link) { dialog, i ->
                openNormalUrl(value.providerUrl)
            }.setNegativeButton(R.string.common_cancel) { dialog, i ->
                dialog.dismiss()
            }.create()
            .show()
    }

    fun logAnalyticsEvent(event: String, param: Bundle) {
        activity.firebaseTracker.logAnalyticsEvent(event, param)
    }

    fun sendUserProperty(key: String, value: String) {
        activity.firebaseTracker.sendUserProperties(key, value)
    }

    fun openNormalUrl(url: String?) {
        try {
             url?.let {
                 val intent = Intent(Intent.ACTION_VIEW, it.parsedUri)
                 activity.startActivity(intent)
            }
        } catch (e: Exception) {
            logException(e)
        }
    }

    fun snackBar(message: String) {
        Snackbar.make(activity.binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    fun snackBar(@StringRes resId: Int) {
        val message = activity.getString(resId)
        snackBar(message)
    }

    fun showAppUpdateDialog(newestVersionCode: Int, marketUrl: String?) {
        AlertDialog.Builder(activity)
            .setTitle(R.string.app_update_title)
            .setMessage(String.format(activity.getString(R.string.app_update_message), newestVersionCode))
            .setPositiveButton(R.string.data_provider_link) { dialog, _ ->
                openMarketUrl(marketUrl)
                dialog.dismiss()
            }.setNegativeButton(R.string.common_cancel) { dialog, _ ->
                dialog.dismiss()
            }.create()
            .show()
    }

}