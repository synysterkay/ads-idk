package com.example.andmoduleads.activity.nativefullscreen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ads.control.ads.AperoAdCallback
import com.ads.control.ads.wrapper.ApAdError
import com.ads.control.helper.adnative.NativeAdConfig
import com.ads.control.helper.adnative.NativeAdHelper
import com.ads.control.helper.adnative.params.NativeAdParam
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.FragmentNativeFullScreenBinding

class FragmentNativeFullScreen : Fragment() {

    private val binding: FragmentNativeFullScreenBinding by lazy {
        FragmentNativeFullScreenBinding.inflate(layoutInflater)
    }

    private val adConfig =
        NativeAdConfig(
            idAds = BuildConfig.native_normal,
            canShowAds = true,
            canReloadAds = true,
            layoutId = R.layout.layout_native_fullscreen
        )

    private val nativeAdHelper by lazy {
        NativeAdHelper(
            context = requireContext(),
            lifecycleOwner = this,
            adConfig
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpShowAds()
    }

    private fun setUpShowAds() {
        nativeAdHelper.registerAdListener(object : AperoAdCallback() {
            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }

            override fun onAdFailedToLoad(adError: ApAdError?) {
                super.onAdFailedToLoad(adError)
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }
        })
        nativeAdHelper.setNativeContentView(binding.contentNativeAd)
        nativeAdHelper.setShimmerLayoutView(binding.loading.shimmerContainerNative)
        nativeAdHelper.requestAds(NativeAdParam.Request.create())
    }
}