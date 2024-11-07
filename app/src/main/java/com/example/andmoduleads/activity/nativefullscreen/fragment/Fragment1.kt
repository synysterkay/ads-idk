package com.example.andmoduleads.activity.nativefullscreen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.Fragment1Binding

class Fragment1 : Fragment() {

    private val binding: Fragment1Binding by lazy {
        Fragment1Binding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}