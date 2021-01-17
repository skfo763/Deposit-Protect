package com.skfo763.depositprotect.admob

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.formats.UnifiedNativeAdView
import com.skfo763.base.extension.logException
import com.skfo763.depositprotect.BuildConfig
import com.skfo763.depositprotect.R
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.IllegalStateException
import java.util.*
import javax.inject.Inject
import kotlin.NullPointerException

@ActivityScoped
class AdMobManager @Inject constructor(
    private val activity: Activity
) : LifecycleObserver {

    private val random = Random()
    @ColorInt private val defaultAdBackground = ContextCompat.getColor(activity, R.color.ad_background_color)

    private var adTemplate: TemplateView? = null
    private val adView = AdView(activity)
    private val mInterstitialAd = InterstitialAd(activity)

    val shouldShowInterstitialAd: Boolean get() = mInterstitialAd.isLoaded && random.nextInt(4) == 0

    private val unifiedAdLoadListener = UnifiedNativeAd.OnUnifiedNativeAdLoadedListener {
        if(activity.isFinishing || activity.isChangingConfigurations) {
            it.destroy()
            return@OnUnifiedNativeAdLoadedListener
        }
        val styles = NativeTemplateStyle.Builder()
            .withMainBackgroundColor(ColorDrawable((defaultAdBackground)))
            .build()
        adTemplate?.apply {
            setStyles(styles)
            setNativeAd(it)
        }
    }

    private val adListener = object: AdListener() {
        override fun onAdFailedToLoad(error: LoadAdError?) = error?.let {
            logException(IllegalStateException(it.message))
        } ?: run {
            logException(NullPointerException())
        }
    }

    init {
        MobileAds.initialize(activity) { }
    }

    fun setTemplateView(templateView: TemplateView) {
        this.adTemplate = templateView
    }

    fun showInterstitialAd() {
        if(shouldShowInterstitialAd) {
            mInterstitialAd.show()
        }
    }

    fun showNativeAd() {
        val adOptions = NativeAdOptions.Builder()
            .setVideoOptions(VideoOptions.Builder().setStartMuted(true).build())
            .build()

        val adLoader = AdLoader.Builder(activity, BuildConfig.AD_MOB_UNIT_NATIVE)
            .forUnifiedNativeAd(unifiedAdLoadListener)
            .withNativeAdOptions(adOptions)
            .withAdListener(adListener)
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun doOnCreate() {
        mInterstitialAd.apply {
            adUnitId = BuildConfig.AD_MOB_UNIT_FULL
            loadAd(AdRequest.Builder().build())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun doOnResume() {
        adView.resume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun doOnPause() {
        adView.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun doOnDestroy() {
        adView.destroy()
    }

    private fun Activity.getAdSize(adViewContainer: View): AdSize {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = adViewContainer.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }
}