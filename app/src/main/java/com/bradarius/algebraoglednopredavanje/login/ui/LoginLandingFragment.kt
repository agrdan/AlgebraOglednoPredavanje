package com.bradarius.algebraoglednopredavanje.login.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.R
import com.bradarius.algebraoglednopredavanje.databinding.FragmentLoginLandingBinding
import com.bradarius.algebraoglednopredavanje.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginLandingFragment : Fragment() {

    private lateinit var binding: FragmentLoginLandingBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginLandingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        if (viewModel.loginData.value?.token != null) {
            binding.llTvWelcome.setText("Welcome, ${viewModel.loginData.value!!.user!!.name} ${viewModel.loginData.value!!.user!!.surname}")
            binding.llTvNote.setText("Your token is: ${viewModel.loginData.value!!.token}.\nYou are successfully logged in!")
        }

    }

    private fun setupToolbar() {
        binding.presentationLandingToolbar.tvTitle.text = "Login landing"
        binding.presentationLandingToolbar.arrowLeft.visibility = View.GONE
        binding.presentationLandingToolbar.arrowRight.setOnClickListener { v -> findNavController().navigate(R.id.action_loginLandingFragment_to_mainFragment) }
    }

}