package technical.test.jetpack.ui.inputamount.router

import android.os.Bundle
import technical.test.jetpack.ui.base.router.BaseRouter

interface AmountRouter: BaseRouter {

    fun setBundleInfo(id:String, bundle: Bundle)
    fun nextScreen()
    fun backScreen()
}