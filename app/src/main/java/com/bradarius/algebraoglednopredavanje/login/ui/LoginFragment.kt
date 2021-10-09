package com.bradarius.algebraoglednopredavanje.login.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.R
import com.bradarius.algebraoglednopredavanje.databinding.FragmentLoginBinding
import com.bradarius.algebraoglednopredavanje.login.model.LoginDto
import com.bradarius.algebraoglednopredavanje.login.model.LoginResponseDto
import com.bradarius.algebraoglednopredavanje.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.scope.bindScope
import org.koin.androidx.viewmodel.ext.android.getViewModel


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.loginDtoData.value?.username != null) {
            val username = viewModel.loginDtoData.value!!.username
            binding.etLoginUsername.setText(username)
        }

        if (viewModel.loginDtoData.value?.password != null) {
            val password = viewModel.loginDtoData.value!!.password
            binding.etLoginPassword.setText(password)
        }

        setupToolbar()

        observeLogin()
        observeLoginData()
        initListeners()

    }

    private fun setupToolbar() {
        binding.loginToolbar.tvTitle.setText("Login")
        binding.loginToolbar.arrowRight.visibility = View.GONE
    }

    private fun initListeners() {
        binding.btnLoginLogin.setOnClickListener { v -> checkFieldsAndLogin() }
        binding.loginToolbar.arrowLeft.setOnClickListener { findNavController().navigateUp() }
    }


    private fun checkFieldsAndLogin() {
        if (!binding.etLoginUsername.text.isNullOrBlank() and !binding.etLoginPassword.text.isNullOrBlank()) {
            val loginDto = LoginDto(binding.etLoginUsername.text.toString(), binding.etLoginPassword.text.toString())
            viewModel.login(loginDto)
        } else {
            Snackbar.make(binding.root, "Username ili password ne mogu biti prazni!", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun observeLoginData() {
        viewModel.loginDtoData.observe(viewLifecycleOwner, Observer {

            Log.d("___test", "${it.username}; ${it.password}")
        })
    }

    private fun observeLogin() {
        viewModel.loginData.observe(viewLifecycleOwner, Observer {

                if (it.token != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_loginLandingFragment)
                }
                if (it.code == 206) {
                    findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)

                }
                if (it.code == 404) {
                    Snackbar.make(binding.root, "Pogresan username ili password", Snackbar.LENGTH_LONG).show()
                }
        })
    }




}