package com.example.andmoduleads.activity.nativefullscreen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andmoduleads.R
import com.example.andmoduleads.databinding.Fragment3Binding


class Fragment3 : Fragment() {

    private val binding by lazy {
        Fragment3Binding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            // navigate to main activity
            requireActivity().finish()
        }
    }

}