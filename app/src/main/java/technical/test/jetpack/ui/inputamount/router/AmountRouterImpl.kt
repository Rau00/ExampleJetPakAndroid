package technical.test.jetpack.ui.inputamount.router

import android.os.Bundle
import technical.test.jetpack.ui.base.router.BaseRouterImpl
import technical.test.jetpack.ui.inputamount.view.AmountFragment
import technical.test.jetpack.ui.result.view.ResultFragment

class AmountRouterImpl: AmountRouter, BaseRouterImpl() {

    override fun nextScreen() {
        navigationViewApp.nextFragment(ResultFragment.newInstance(), AmountFragment.TAG)
    }

    override fun backScreen() {
        navigationViewApp.backFragment()
    }

    override fun setBundleInfo(id:String, bundle: Bundle) {
        navigationViewApp.setupBundle(id, bundle)
    }
}