package com.example.ch3.section4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ch3.databinding.FragmentBBinding

class BFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBBinding.inflate(inflater)

        binding.button.setOnClickListener {
            val controller = Navigation.findNavController(it)
            controller.navigateUp()
        }

        val bundle = arguments
        val aArg = bundle?.getString("aArg")
        val bArg = bundle?.getInt("bArg")

        Toast.makeText(activity, "$aArg, $bArg", Toast.LENGTH_SHORT).show()

        return binding.root
    }
}