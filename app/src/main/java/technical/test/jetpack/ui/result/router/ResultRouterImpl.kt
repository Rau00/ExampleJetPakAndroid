package technical.test.jetpack.ui.result.router

import technical.test.jetpack.ui.base.router.BaseRouterImpl

class ResultRouterImpl: ResultRouter, BaseRouterImpl() {

    override fun backStep() {
        navigationViewApp.backFragment()
    }

}