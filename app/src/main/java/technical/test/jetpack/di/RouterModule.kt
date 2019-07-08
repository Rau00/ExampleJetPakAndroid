package technical.test.jetpack.di

import org.koin.dsl.module
import technical.test.jetpack.ui.inputamount.router.AmountRouter
import technical.test.jetpack.ui.inputamount.router.AmountRouterImpl
import technical.test.jetpack.ui.main.router.MainRouter
import technical.test.jetpack.ui.main.router.MainRouterImpl
import technical.test.jetpack.ui.result.router.ResultRouter
import technical.test.jetpack.ui.result.router.ResultRouterImpl


val routerModule = module {
    single { MainRouterImpl() as MainRouter }
    single { AmountRouterImpl() as AmountRouter }
    single { ResultRouterImpl() as ResultRouter }
}