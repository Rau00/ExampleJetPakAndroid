package technical.test.jetpack.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout

import kotlinx.android.synthetic.main.activity_main.*
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.main.view.MainFragment
import technical.test.jetpack.utils.ui.DimensionsUtils
import technical.test.jetpack.R
import technical.test.jetpack.ui.interfacebase.CollapsableHeaderConfiguration
import technical.test.jetpack.utils.Constants

class MainActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener, NavigationView,
    CollapsableHeaderConfiguration {

    private var collapsed:Int = 0

    private var expanded: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            nextFragment(MainFragment.newInstance(), MainFragment.TAG, addToBackStack = false)
        }
        appBarLayout.addOnOffsetChangedListener(this)
        initSizes()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initSizes() {
        expanded = DimensionsUtils.dpToPixels(this, 115f)
        collapsed = DimensionsUtils.dpToPixels(this, 0f)
    }

    override fun onOffsetChanged(p0: AppBarLayout?, y: Int) {
        val percent = Math.abs(1 - (1 - Math.abs(y.toFloat()) / (expanded - collapsed).toFloat()))
        var reversePercent = 1 - Math.abs(y.toFloat()) / (expanded - collapsed).toFloat()

       //TODO prepared to scale or move component to animation
    }

    override fun nextFragment(fragment: Fragment, tag: String, addToBackStack: Boolean) {
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()

        appBarLayout.setExpanded(true)
        if (addToBackStack) {
            ft.addToBackStack(tag)
        }
        ft.replace(R.id.content, fragment, tag)
        ft.commitAllowingStateLoss()
    }

    override fun backFragment() {
        onBackPressed()
    }

    override fun setupBundle(id:String, infoBundle: Bundle) {
        intent.putExtra(id, infoBundle)
    }

    override fun changeText(text: String) {
        tvCollapsable.text = text
    }
}
