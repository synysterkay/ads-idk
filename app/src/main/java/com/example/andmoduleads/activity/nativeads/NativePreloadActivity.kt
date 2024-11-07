package com.example.andmoduleads.activity.nativeads

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ads.control.helper.adnative.NativeAdConfig
import com.ads.control.helper.adnative.preload.NativeAdPreload
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.ActivityNativePreloadBinding

class NativePreloadActivity : AppCompatActivity() {
    companion object {
        const val PRELOAD_KEY = "NativePreloadActivity"
    }

    private lateinit var binding: ActivityNativePreloadBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNativePreloadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Preload by id ads
        preloadNativeByIdAds()
        //Preload by custom key
        preloadNativeByKey()

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, NativePreloadShowActivity::class.java))
        }
    }

    private fun preloadNativeByIdAds() {
        NativeAdPreload.getInstance().preload(
            this,
            NativeAdConfig(
                idAds = BuildConfig.native_normal,
                canShowAds = true,
                canReloadAds = true,
                layoutId = R.layout.layout_native_ads
            )
        )
    }

    private fun preloadNativeByKey() {
        NativeAdPreload.getInstance().preloadWithKey(
            PRELOAD_KEY,
            this,
            NativeAdConfig(
                idAds = BuildConfig.ad_native_high_priority,
                canShowAds = true,
                canReloadAds = true,
                layoutId = R.layout.layout_native_ads
            ),
            1
        )
    }
}