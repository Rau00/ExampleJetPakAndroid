package technical.test.jetpack.di

import org.koin.dsl.module
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.common.MainActivity
import technical.test.jetpack.ui.inputamount.view.AmountFragment
import technical.test.jetpack.ui.inputamount.view.AmountView
import technical.test.jetpack.ui.main.view.MainFragment
import technical.test.jetpack.ui.main.view.MainView
import technical.test.jetpack.ui.result.view.ResultFragment
import technical.test.jetpack.ui.result.view.ResultView

val viewyModule = module {
    single { MainActivity() as NavigationView}
    single { MainFragment() as MainView }
    single { AmountFragment() as AmountView }
    single { ResultFragment() as ResultView }
}