package technical.test.jetpack.ui.main.router

import android.os.Bundle
import technical.test.jetpack.ui.base.router.BaseRouterImpl
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.inputamount.view.AmountFragment

class MainRouterImpl: MainRouter, BaseRouterImpl() {

    override fun nextScreen() {
        navigationViewApp.nextFragment(AmountFragment.newInstance(), AmountFragment.TAG)
    }

    override fun setBundleInfo(id:String, bundle: Bundle) {
        navigationViewApp.setupBundle(id, bundle)
    }
}
