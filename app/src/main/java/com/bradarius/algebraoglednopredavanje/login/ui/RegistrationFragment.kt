package com.bradarius.algebraoglednopredavanje.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.R
import com.bradarius.algebraoglednopredavanje.databinding.FragmentRegistrationBinding
import com.bradarius.algebraoglednopredavanje.login.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        initListeners()
    }

    private fun initListeners() {
        binding.registrationToolbar.arrowLeft.setOnClickListener { v -> navigateBack() }
    }

    private fun setupToolbar() {
        binding.registrationToolbar.tvTitle.setText("Registration")
        binding.registrationToolbar.arrowRight.visibility = View.GONE
    }

    private fun navigateBack() {
        viewModel.clearLogin()
        findNavController().navigateUp()
    }

}