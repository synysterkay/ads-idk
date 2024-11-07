package com.example.andmoduleads.activity.nativeads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ads.control.ads.AperoAdCallback
import com.ads.control.ads.wrapper.ApAdError
import com.ads.control.helper.adnative.NativeAdConfig
import com.ads.control.helper.adnative.NativeAdHelper
import com.ads.control.helper.adnative.highfloor.NativeAdHighFloorConfig
import com.ads.control.helper.adnative.highfloor.NativeAdHighFloorHelper
import com.ads.control.helper.adnative.params.NativeAdParam
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.ActivityNativeBinding

class NativeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nativeAdHelperNormal = initNativeAdsNormal()
        val nativeAdHelperHighPriority = initNativeAdsHighPriority()

        //set container ads
        nativeAdHelperNormal
            .setNativeContentView(binding.layoutAdNativeNormal)
            .setShimmerLayoutView(binding.layoutShimmer.shimmerContainerNative)

        //load ads
        nativeAdHelperHighPriority
            .setNativeContentView(binding.layoutAdNativeHighPriority)
            .setShimmerLayoutView(binding.layoutShimmerHighPriority.shimmerContainerNative)

        nativeAdHelperNormal.requestAds(NativeAdParam.Request.create())
        nativeAdHelperHighPriority.requestAds(NativeAdParam.Request.create())
    }

    private fun initNativeAdsNormal(): NativeAdHelper {
        val adsConfig = getNativeConfigNormal()
        return NativeAdHelper(this, this, config = adsConfig).apply {
            registerAdListener(object : AperoAdCallback() {
            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }

            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(adError: ApAdError?) {
                super.onAdFailedToLoad(adError)
            }

            override fun onAdFailedToShow(adError: ApAdError?) {
                super.onAdFailedToShow(adError)
            }
        })
        }
    }

    private fun getNativeConfigNormal(): NativeAdConfig {
        return NativeAdConfig(
            idAds = BuildConfig.native_normal,
            canShowAds = true,
            canReloadAds = true,
            layoutId = R.layout.layout_native_ads
        )
    }

    private fun initNativeAdsHighPriority(): NativeAdHighFloorHelper {
        val adsConfig = getNativeConfigHighPriority()
        return NativeAdHighFloorHelper(this, this, config = adsConfig).apply {
            registerAdListener(object : AperoAdCallback() {
                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }

                override fun onAdFailedToLoad(adError: ApAdError?) {
                    super.onAdFailedToLoad(adError)
                }

                override fun onAdFailedToShow(adError: ApAdError?) {
                    super.onAdFailedToShow(adError)
                }
            })
        }
    }

    private fun getNativeConfigHighPriority(): NativeAdHighFloorConfig {
        return NativeAdHighFloorConfig(
            idAdAllPrice = BuildConfig.native_normal,
            idAdHighFloor = BuildConfig.ad_native_high_priority,
            canShowAds = true,
            canReloadAds = true,
            layoutId = R.layout.layout_native_ads
        )
    }
}