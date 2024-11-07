package com.example.andmoduleads.activity.banner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ads.control.funtion.AdCallback
import com.ads.control.helper.banner.BannerAd2FloorConfig
import com.ads.control.helper.banner.BannerAdConfig
import com.ads.control.helper.banner.BannerAdHelper
import com.ads.control.helper.banner.params.BannerAdParam
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.databinding.ActivitySampleBannerBinding

class SampleBannerActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySampleBannerBinding.inflate(layoutInflater) }
    private val bannerAdHelper by lazy { initBannerHelper() }
    private val canShowAds = true // true : show ads, false : donn't show ads
    private val canReloadAd = true // true : reload ads, false : donn't reload ads
    private val callback = object : AdCallback() {
        override fun onAdClicked() {
            super.onAdClicked()
            // when click banner
        }

        override fun onAdImpression() {
            super.onAdImpression()
            // when view banner
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bannerAdHelper.setBannerContentView(binding.bannerAdView)
        bannerAdHelper.registerAdListener(callback)
        bannerAdHelper.requestAds(BannerAdParam.Request.create())

//        When you want to reload banner when click something, user this function, 2000 (2 second) is minimum banner was displayed before, Avoid spam requests
//        bannerAdHelper.requestAds(BannerAdParam.Clickable(2000))
    }

    private fun initBannerHelper(): BannerAdHelper {
        val config = BannerAdConfig(
            BuildConfig.banner_normal,
            canShowAds,
            canReloadAd,
        )
        return BannerAdHelper(activity = this, lifecycleOwner = this, config = config)
    }
    fun initBannerHelperPriority() : BannerAdHelper{
        val config = BannerAd2FloorConfig(
            idAds = BuildConfig.banner_normal,
            idAds2Floor = BuildConfig.ads_inter_priority,
            canShowAds = canShowAds,
            canReloadAds = canReloadAd
        )
        return BannerAdHelper(activity = this, lifecycleOwner = this, config = config)
    }
}