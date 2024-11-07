package com.example.andmoduleads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andmoduleads.activity.banner.SampleBannerActivity;
import com.example.andmoduleads.activity.interstitials.InterstitialsActivityPreload;
import com.example.andmoduleads.activity.nativeads.NativeActivity;
import com.example.andmoduleads.activity.nativeads.NativePreloadActivity;
import com.example.andmoduleads.activity.nativefullscreen.NativeFullscreenActivity;
import com.example.andmoduleads.activity.reward.SampleRewardActivity;
import com.example.andmoduleads.appopenads.ResumeAppOpenAdActivity;
import com.example.andmoduleads.appopenads.SplashAppOpenAdActivity;
import com.example.andmoduleads.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //adjust
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleClickActions();
    }

    private void handleClickActions() {
        binding.btnBanner.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SampleBannerActivity.class)));
        binding.btnResumedAdOpen.setOnClickListener(v -> {
            startActivity(new Intent(this, ResumeAppOpenAdActivity.class));
        });

        binding.btnAdOpenSplash.setOnClickListener(v -> {
            startActivity(new Intent(this, SplashAppOpenAdActivity.class));
        });

        binding.btnNative.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NativeActivity.class);
            startActivity(intent);
        });

        binding.btnNativePreload.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NativePreloadActivity.class);
            startActivity(intent);
        });

        binding.btnNativeFullScreen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NativeFullscreenActivity.class);
            startActivity(intent);
        });

        binding.btnReward.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SampleRewardActivity.class);
            startActivity(intent);
        });
        binding.btnInter.setOnClickListener(v -> {
            InterstitialsActivityPreload.Companion.startInterstitialsActivityPreload(this);
        });
        binding.btnSubscription.setOnClickListener(v -> {

        });
    }
}
