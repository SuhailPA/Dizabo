package com.example.dizabo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.dizabo.R
import com.example.dizabo.databinding.FragmentOtpBinding


class OtpFragment : Fragment() {

    private lateinit var binding: FragmentOtpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(inflater,container,false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.apply {
            verifyOtp.setOnClickListener {
                root.findNavController().navigate(OtpFragmentDirections.actionOtpFragmentToLoginFragment())
            }
        }
    }


}