package com.example.ch3.section1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.ch3.databinding.FragmentOneBinding
import com.example.ch3.databinding.FragmentTwoBinding

class TwoFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTwoBinding.inflate(inflater)

        //case 1 - viewModels()
//        val viewModel: MyApplicationViewModel by viewModels()

        //case 2
        val viewModel: MyApplicationViewModel by activityViewModels()

        binding.button1.setOnClickListener {
            viewModel.count++
        }
        binding.button2.setOnClickListener {
            Toast.makeText(activity, "count: ${viewModel.count}",
                Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}