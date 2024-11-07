package com.example.andmoduleads.activity.reward

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andmoduleads.BuildConfig
import com.example.andmoduleads.databinding.ActivitySampleRewardBinding

class SampleRewardActivity : AppCompatActivity() {
    private val binding: ActivitySampleRewardBinding by lazy {
        ActivitySampleRewardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        RewardAdsManage.loadReward(this, BuildConfig.id_reward_priority, BuildConfig.id_reward_default)

        var coin = 0
        binding.btnShowAds.setOnClickListener {
            RewardAdsManage.showReward(this, BuildConfig.id_reward_priority, BuildConfig.id_reward_default, true) {
                coin += 10
                binding.txtResult.text = coin.toString()
            }
        }
    }
}