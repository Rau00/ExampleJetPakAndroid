package technical.test.jetpack.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import technical.test.jetpack.ui.inputamount.viewmodel.AmountViewModelImpl
import technical.test.jetpack.ui.main.viewmodel.MainViewModelImpl
import technical.test.jetpack.ui.result.viewmodel.ResultViewModelImpl

val viewModelModule = module {
    viewModel { MainViewModelImpl(get(), get()) }
    viewModel { AmountViewModelImpl(get()) }
    viewModel { ResultViewModelImpl(get()) }
}