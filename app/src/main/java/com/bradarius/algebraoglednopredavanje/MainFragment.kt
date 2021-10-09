package com.bradarius.algebraoglednopredavanje

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnPresentation.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_presentationFragment) }
        binding.btnPractice.setOnClickListener { findNavController().navigate(R.id.action_mainFragment_to_loginFragment) }

    }


}