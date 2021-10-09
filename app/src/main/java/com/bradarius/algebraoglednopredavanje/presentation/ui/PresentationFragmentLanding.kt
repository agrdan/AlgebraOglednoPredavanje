package com.bradarius.algebraoglednopredavanje.presentation.ui

import android.os.Bundle
import android.transition.Visibility
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.R
import com.bradarius.algebraoglednopredavanje.databinding.FragmentPresentationLandingBinding

class PresentationFragmentLanding : Fragment() {

    val user by lazy {
        PresentationFragmentLandingArgs.fromBundle(requireArguments()).pflUser
    }

    val counter by lazy {
        PresentationFragmentLandingArgs.fromBundle(requireArguments()).pflCounter
    }

    private lateinit var binding: FragmentPresentationLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentationLandingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        loadValues()
    }

    private fun loadValues() {
        binding.tvCounterValue.text = "Counter value: $counter"
        if (user != null) {
            binding.tvNameValue.text = "Ime: ${user!!.name}"
            binding.tvSurnameValue.text = "Prezime: ${user!!.surname}"
        }
    }


    fun setupToolbar() {
        binding.presentationLandingToolbar.tvTitle.setText("Presentation Landing")
        binding.presentationLandingToolbar.arrowLeft.visibility = View.GONE
        binding.presentationLandingToolbar.arrowRight.setOnClickListener { v -> goNext() }
    }

    fun goNext() {
        val directions = PresentationFragmentLandingDirections.actionPresentationFragmentLandingToMainFragment()
        findNavController().navigate(directions)
    }



}