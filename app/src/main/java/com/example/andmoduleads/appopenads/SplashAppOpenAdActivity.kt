package com.example.andmoduleads.appopenads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ads.control.ads.appopenad.AppOpenResult
import com.ads.control.manager.AppOpenAdManager
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.databinding.ActivitySplashAppOpenAdBinding
import kotlinx.coroutines.launch

class SplashAppOpenAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashAppOpenAdBinding
    private var appOpenResult: AppOpenResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashAppOpenAdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleLoadAd()
        handleShowAd()
    }

    private fun handleLoadAd() {
        lifecycleScope.launch {
            // If you need to load AppOpenAd with normal Id
//            appOpenResult = AppOpenAdManager.loadAppOpenAd(
//                this@SplashAppOpenAdActivity,
//                BuildConfig.ads_open_app
//            )

            // If you need to load AppOpenAd with high Id
            appOpenResult = AppOpenAdManager.loadAppOpenAdHighFloor(
                this@SplashAppOpenAdActivity,
                BuildConfig.ads_open_app_high,
                BuildConfig.ads_open_app
            )
        }
    }

    private fun handleShowAd() {
        binding.btnShowAd.setOnClickListener {
            lifecycleScope.launch {
                if (appOpenResult != null) {
                    AppOpenAdManager.showAppOpenAd(
                        activity = this@SplashAppOpenAdActivity,
                        appOpenResult = appOpenResult!!,      // If appOpenResult is null you should perform the next action
                        onNextAction = {
                            finish()
                        }, // Called when ad is closed
                        onAdClick = {
                            Log.d("AppOpenAdManager", "ad clicked")
                        }, // Called when ad is clicked
                        onAdImpression = {
                            Log.d("AppOpenAdManager", "ad impression");
                        }, // Called when ad is displayed
                    )
                } else {
                    // If appOpenResult is null you should perform the next action here
                }

            }
        }
    }

}