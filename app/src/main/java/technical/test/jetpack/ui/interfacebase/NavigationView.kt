package technical.test.jetpack.ui.interfacebase

import android.os.Bundle
import androidx.fragment.app.Fragment

interface NavigationView {

    fun nextFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = true)
    fun backFragment()
    fun setupBundle(id:String, infoBundle: Bundle)
}