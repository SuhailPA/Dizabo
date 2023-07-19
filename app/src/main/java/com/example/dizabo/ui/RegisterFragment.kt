package com.example.dizabo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.dizabo.R
import com.example.dizabo.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var binding : FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        initUI()
        return binding?.root
    }

    private fun initUI() {
        binding?.signUpButton?.setOnClickListener {
            binding?.root?.findNavController()?.navigate(RegisterFragmentDirections.actionRegisterFragmentToOtpFragment())
        }
    }


}