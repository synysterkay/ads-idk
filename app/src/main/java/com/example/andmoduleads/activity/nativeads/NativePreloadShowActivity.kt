package com.example.andmoduleads.activity.nativeads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ads.control.helper.adnative.NativeAdConfig
import com.ads.control.helper.adnative.NativeAdHelper
import com.ads.control.helper.adnative.params.NativeAdParam
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.ActivityNativePreloadShowBinding

class NativePreloadShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativePreloadShowBinding

    private val nativeAdHelperTop by lazy {
        NativeAdHelper(
            this,
            this,
            NativeAdConfig(
                idAds = BuildConfig.native_normal,
                canShowAds = true,
                canReloadAds = true,
                layoutId = R.layout.layout_native_ads
            )
        ).apply {
            setEnablePreload(true)
        }
    }

    private val nativeAdHelperBottom by lazy {
        NativeAdHelper(
            this,
            this,
            NativeAdConfig(
                idAds = BuildConfig.ad_native_high_priority,
                canShowAds = true,
                canReloadAds = true,
                layoutId = R.layout.layout_native_ads
            )
        ).apply {
            setEnablePreloadWithKey(true, NativePreloadActivity.PRELOAD_KEY)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativePreloadShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nativeAdHelperTop.setNativeContentView(binding.layoutNativeTop)
        nativeAdHelperTop.setShimmerLayoutView(binding.layoutShimmerTop.shimmerContainerNative)
        nativeAdHelperTop.requestAds(NativeAdParam.Request.create())

        nativeAdHelperBottom.setNativeContentView(binding.layoutNativeBottom)
        nativeAdHelperBottom.setShimmerLayoutView(binding.layoutShimmerBottom.shimmerContainerNative)
        nativeAdHelperBottom.requestAds(NativeAdParam.Request.create())
    }
}