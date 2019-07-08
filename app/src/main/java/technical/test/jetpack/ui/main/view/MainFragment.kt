package technical.test.jetpack.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import technical.test.jetpack.ui.main.viewmodel.MainViewModelImpl
import technical.test.jetpack.ui.main.view.adapter.ContactsAdapter
import java.util.Arrays.asList
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import technical.test.jetpack.R

class MainFragment : Fragment(), MainView {
    companion object {
        val TAG = this.javaClass.simpleName
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModelImpl
    private lateinit var contactsAdapter: ContactsAdapter
    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = getViewModel()
        mainViewModel.initView(this)
        setUpObservers()
        setupAnimations()
        fillViews()
    }

    private fun fillViews() {
        setUpContactAdapter()
        contactList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = contactsAdapter
        }
        mainNavigationComponent.actionNext { mainViewModel.nextStep() }
        setupScrollListener()
    }

    private fun setUpObservers() {
        mainViewModel.getSuperHeros().observe(viewLifecycleOwner, Observer { contacts ->
            contactsAdapter.setContactList(contacts)
            contactsAdapter.notifyDataSetChanged()
            contactList.visibility = View.VISIBLE
            showLoading(false)
        })
    }

    private fun setUpContactAdapter() {
        contactsAdapter = ContactsAdapter(asList()) { viewHolder ->
            mainViewModel.itemClicked(viewHolder.getContactModel())
            viewHolder.updateSelection()
        }
    }

    private fun setupAnimations() {
        fabOpen = AnimationUtils.loadAnimation(activity, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(activity, R.anim.fab_close)
    }

    override fun showLoading(value: Boolean) {
        when (value){
            true -> {
                progressBar.visibility = View.VISIBLE
            }
            false -> {
                progressBar.visibility = View.GONE
            }
        }
    }

    fun setupScrollListener() {
        contactList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var lastItemVisible = 0
            private var currentItemVisible = 0
            private var isToUp = true

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItemVisible = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (lastItemVisible < currentItemVisible && isToUp) {
                    mainNavigationComponent.startAnimation(fabClose)
                    isToUp = false
                }
                if (lastItemVisible > currentItemVisible && !isToUp) {
                    mainNavigationComponent.startAnimation(fabOpen)
                    isToUp = true
                }
                lastItemVisible = currentItemVisible;
            }
        })
    }

    override fun onDetach() {
        super.onDetach()

        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)
        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }

    }
}
