package com.bradarius.algebraoglednopredavanje.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.bradarius.algebraoglednopredavanje.R
import com.bradarius.algebraoglednopredavanje.databinding.FragmentPresentationBinding
import com.bradarius.algebraoglednopredavanje.presentation.model.User
import com.google.android.material.snackbar.Snackbar


class PresentationFragment : Fragment() {

    private lateinit var binding: FragmentPresentationBinding
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPresentationBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.presentationToolbar.tvTitle.setText("Presentation")
        initListeners()
    }

    fun initListeners() {
        binding.presentationToolbar.arrowLeft.setOnClickListener { v -> findNavController().navigateUp() }
        binding.presentationToolbar.arrowRight.setOnClickListener { v -> onNext() }
        binding.btnCounter.setOnClickListener { v -> counterHandler() }
        binding.btnNext.setOnClickListener { v -> onNext() }
    }

    fun counterHandler() {
        binding.tvCounter.text = (++counter).toString()
    }

    fun onNext() {
        if (areFieldsValid() == NO_ERROR) {
            val user = User(binding.etName.text.toString(), binding.etSurname.text.toString())
            val direction = PresentationFragmentDirections.actionPresentationFragmentToPresentationFragmentLanding(counter, user)
            findNavController().navigate(direction)
        }
    }

    fun areFieldsValid(): Int {

        var errorCode = 0
        if (binding.etName.text.isNullOrBlank()) {
            errorCode = errorCode or NAME_INVALID_CODE
        }

        if (binding.etSurname.text.isNullOrBlank()) {
            errorCode = errorCode or SURNAME_INVALID_CODE

        }

        if ((errorCode and NAME_INVALID_CODE) == NAME_INVALID_CODE &&
            (errorCode and SURNAME_INVALID_CODE) == SURNAME_INVALID_CODE) {
            Snackbar.make(binding.root, "Ime i prezime nemogu biti prazni", Snackbar.LENGTH_LONG).show()
        }
        else if ((errorCode and NAME_INVALID_CODE) == NAME_INVALID_CODE) {
            Snackbar.make(binding.root, "Ime ne može biti prazno", Snackbar.LENGTH_LONG).show()
        }
        else if ((errorCode and SURNAME_INVALID_CODE) == SURNAME_INVALID_CODE) {
            Snackbar.make(binding.root, "Prezime ne može biti prazno", Snackbar.LENGTH_LONG).show()
        }
        return errorCode
    }

    companion object {
        const val NO_ERROR = 0
        const val NAME_INVALID_CODE = 1
        const val SURNAME_INVALID_CODE = 2
    }


}