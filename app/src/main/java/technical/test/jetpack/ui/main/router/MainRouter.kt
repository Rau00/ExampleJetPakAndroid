package technical.test.jetpack.ui.main.router

import android.os.Bundle
import technical.test.jetpack.ui.base.router.BaseRouter

interface MainRouter: BaseRouter {
    fun nextScreen()
    fun setBundleInfo(id:String, bundle: Bundle)
}