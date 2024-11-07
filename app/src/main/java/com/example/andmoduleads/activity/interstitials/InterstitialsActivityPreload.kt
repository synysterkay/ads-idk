package com.example.andmoduleads.activity.interstitials

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.ads.control.manager.InterstitialAdManager
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.MyApplication
import com.example.andmoduleads.R
import kotlinx.coroutines.launch

class InterstitialsActivityPreload : AppCompatActivity() {
    companion object {
        fun startInterstitialsActivityPreload(context: Context) {
            val intent = Intent(context, InterstitialsActivityPreload::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_interstitials_preload)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycleScope.launch {
            MyApplication.getApplication().storageCommon.interstitialAd =
                InterstitialAdManager.loadInterstitialAd(
                    this@InterstitialsActivityPreload,
                    BuildConfig.ad_interstitial
                )
        }

        val button = findViewById<AppCompatButton>(R.id.showInter)
        button.setOnClickListener {
            val apInterstitialsActivity = MyApplication.getApplication().storageCommon.interstitialAd
            InterstitialAdManager.showInterstitialAd(
                activity = this,
                interstitialAd = apInterstitialsActivity,
                onNextAction = {
                    startActivity(Intent(this, InterstitialsActivity::class.java))
                },
                onAdClicked = {},
                onAdImpression = {}
            )
            MyApplication.getApplication().storageCommon.interstitialAd = null
        }
    }
}