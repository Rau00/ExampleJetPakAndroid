package technical.test.jetpack.ui.base.router

import technical.test.jetpack.ui.interfacebase.NavigationView

open class BaseRouterImpl: BaseRouter {

    lateinit var navigationViewApp: NavigationView

    override fun setNavigationView(value: NavigationView) {
        this.navigationViewApp = value
    }


}