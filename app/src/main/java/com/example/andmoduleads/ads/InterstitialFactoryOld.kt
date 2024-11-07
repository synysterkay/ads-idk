package com.example.andmoduleads.ads

import android.content.Context
import com.ads.control.ads.AperoAd
import com.ads.control.ads.AperoAdCallback
import com.ads.control.ads.wrapper.ApAdError
import com.ads.control.ads.wrapper.ApInterstitialAd
import com.facebook.ads.InterstitialAdListener

object InterstitialFactoryOld {
    private fun loadAdInterstitial(
        context: Context,
        adUnitId: String,
        onAdLoaded: (ApInterstitialAd?) -> Unit,
        onAdFailedToLoad: (ApAdError?) -> Unit
    ) {
        AperoAd.getInstance().getInterstitialAds(context, adUnitId, object : AperoAdCallback() {
            override fun onInterstitialLoad(interstitialAd: ApInterstitialAd?) {
                super.onInterstitialLoad(interstitialAd)
                onAdLoaded(interstitialAd)
            }

            override fun onAdFailedToLoad(adError: ApAdError?) {
                super.onAdFailedToLoad(adError)
                onAdFailedToLoad(adError)
            }
        })
    }


    private fun loadAdInterstitialPriority(
        context: Context,
        adUnitIdPriority: String,
        adUnitIdDefault: String,
        onAdLoaded: (ApInterstitialAd?) -> Unit,
        onAdFailedToLoad: (ApAdError?) -> Unit
    ) {
        loadAdInterstitial(context, adUnitIdPriority,
            onAdLoaded = { interstitialAd ->
                if (interstitialAd != null) {
                    onAdLoaded(interstitialAd)
                } else {
                    loadAdInterstitial(context, adUnitIdDefault,
                        onAdLoaded = { defaultAd ->
                            onAdLoaded(defaultAd)
                        },
                        onAdFailedToLoad = onAdFailedToLoad
                    )
                }
            },
            onAdFailedToLoad = onAdFailedToLoad
        )
    }


    private fun showInterstitialAd(
        context: Context,
        interstitialAd: ApInterstitialAd?,
        onAdClicked: () -> Unit,
        onAdImpression: () -> Unit,
        onNextAction: () -> Unit
    ) {
        val aperoCallback = object : AperoAdCallback() {
            override fun onNextAction() {
                super.onNextAction()
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdClicked() {
                super.onAdClicked()
            }
        }
    }
}