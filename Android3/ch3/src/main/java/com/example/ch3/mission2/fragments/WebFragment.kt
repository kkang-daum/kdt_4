package com.example.ch3.mission2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ch3.databinding.FragmentWebBinding

//class WebFragment(val url: String): Fragment() {
class WebFragment(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWebBinding.inflate(inflater)

//        binding.webview.loadUrl(url)

        //전달되는 argument 획득...
        val bundle = arguments
        bundle?.let {
            //Fragment name + Args : safearg 에서 자동 generate
            WebFragmentArgs.fromBundle(bundle).also {
                binding.webview.loadUrl(it.url)
            }
        }


        return binding.root
    }
}