package com.example.andmoduleads.appopenads

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ads.control.admob.AppOpenManager
import com.example.andmoduleads.databinding.ActivityResumeAppOpenAdBinding

class ResumeAppOpenAdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResumeAppOpenAdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResumeAppOpenAdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleActions()
    }

    private fun handleActions() {
        binding.btnDisableAdResumeOnce.setOnClickListener {
            AppOpenManager.getInstance().disableAdResumeByClickAction()
        }
        binding.btnDisableAdResumeWithActivity.setOnClickListener {
            AppOpenManager.getInstance()
                .disableAppResumeWithActivity(ResumeAppOpenAdActivity::class.java)
        }
    }

}