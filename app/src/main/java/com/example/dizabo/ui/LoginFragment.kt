package com.example.dizabo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.dizabo.R
import com.example.dizabo.data.LoginRequestBody
import com.example.dizabo.databinding.FragmentLoginBinding
import com.example.dizabo.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        initUi()
        return binding?.root
    }

    private fun initUi() {
        binding?.apply {
            signInButton.setOnClickListener {
                if (userEmail.editText?.text?.isNotEmpty() == true && userPassword.editText?.text?.isNotEmpty() == true) {
                    val userDetails = LoginRequestBody(
                        userEmail.editText?.text.toString(),
                        userPassword.editText?.text.toString()
                    )
                    userLoginFlow(userDetails)
                }
            }
            registerButton.setOnClickListener {
                root.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun userLoginFlow(userDet: LoginRequestBody) {
        lifecycleScope.launch {
            loginViewModel.loginUser(userDet).collect { result ->
                if (result.isSuccess) {
                    binding?.root?.findNavController()
                        ?.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                } else {
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}