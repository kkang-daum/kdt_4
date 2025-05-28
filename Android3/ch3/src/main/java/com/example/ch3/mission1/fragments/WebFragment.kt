package com.example.ch3.mission1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch3.databinding.FragmentWebBinding

class WebFragment(val url: String): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWebBinding.inflate(inflater)

        binding.webview.loadUrl(url)

        return binding.root
    }
}