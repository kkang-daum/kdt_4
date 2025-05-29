package com.example.ch3.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ch3.R
import com.example.ch3.databinding.FragmentABinding

class AFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentABinding.inflate(inflater)

        binding.button.setOnClickListener {
            val controller = Navigation.findNavController(it)
            //case 1 - 이동 대상의 destination id 등록으로..
//            controller.navigate(R.id.BFragment, savedInstanceState)

            //case 2 - nav graph 의 action id 로 이동..

            val bundle = Bundle()
            bundle.putString("aArg", "hello")
            bundle.putInt("bArg", 20)

            controller.navigate(R.id.action_AFragment_to_BFragment3, bundle)
        }

        return binding.root
    }
}