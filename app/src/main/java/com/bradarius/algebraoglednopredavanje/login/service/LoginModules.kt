package com.bradarius.algebraoglednopredavanje.login.service

import com.bradarius.algebraoglednopredavanje.login.viewmodel.LoginViewModel
import com.bradarius.algebraoglednopredavanje.repository.LoginRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        LoginRepository()
    }
}

val viewModelModule = module {
    viewModel {
        LoginViewModel(get())
    }
}