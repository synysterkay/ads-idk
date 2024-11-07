package com.example.andmoduleads.activity.nativefullscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.andmoduleads.activity.nativefullscreen.adapter.ViewPagerAdapter
import com.example.andmoduleads.activity.nativefullscreen.fragment.Fragment1
import com.example.andmoduleads.activity.nativefullscreen.fragment.FragmentNativeFullScreen
import com.example.andmoduleads.activity.nativefullscreen.fragment.Fragment3
import com.example.andmoduleads.databinding.ActivityNativeFullscreenBinding

class NativeFullscreenActivity : AppCompatActivity() {

    private val binding: ActivityNativeFullscreenBinding by lazy {
        ActivityNativeFullscreenBinding.inflate(layoutInflater)
    }

    private val listPage = listOf(
        Fragment1(),
        FragmentNativeFullScreen(),
        Fragment3()
    )

    private val viewPagerAdapter by lazy {
        ViewPagerAdapter(this, listPage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager.adapter = viewPagerAdapter
    }
}