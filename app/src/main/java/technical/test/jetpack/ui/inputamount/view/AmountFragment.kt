package technical.test.jetpack.ui.inputamount.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.amount_fragment.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

import technical.test.jetpack.R
import technical.test.jetpack.ui.inputamount.viewmodel.AmountViewModelImpl
import technical.test.jetpack.utils.Constants

class AmountFragment : Fragment(), AmountView {

    companion object {
        val TAG = this.javaClass.simpleName
        fun newInstance() = AmountFragment()
    }

    private lateinit var viewModel: AmountViewModelImpl

    private lateinit var shake: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.amount_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel()
        viewModel.initView(this)
        shake = AnimationUtils.loadAnimation(this.context, R.anim.shake)
        setupListener()
        setupObserverView()
        setUpValidateInput()
    }

    private fun setupListener() {
        amountNavigationComponent.actionNext { viewModel.nextStep() }
        amountNavigationComponent.actionBack { viewModel.backStep() }
    }

    private fun setupObserverView() {
        viewModel.finalAmount.observe(viewLifecycleOwner, Observer {
            tvAmount.text = String.format(getString(R.string.amount_input_info), it)
        })
    }

    private fun setUpValidateInput() {
        textInput.addTextChangedListener(object : TextWatcher {

            var jobcheck = setupInputJob(Constants.EMPTY)

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.prepareAmountToProcess(text.toString())

                if (viewModel.validateAmountlength().not()) {
                    viewModel.updateInputLength()
                    textInputLayout.startAnimation(shake)
                    return
                }
                if (jobcheck.isActive) {
                    jobcheck.cancel()
                }
                jobcheck = setupInputJob(viewModel.amount)
                viewModel.finalAmount.value = viewModel.formatAmount()
            }

            override fun afterTextChanged(s: Editable?) {
                textInput.removeTextChangedListener(this)
                val amountFormatted = viewModel.formatAmount()
                textInput.setText(amountFormatted)
                textInput.setSelection(viewModel.amount.length)
                textInput.addTextChangedListener(this)
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
                //Not used
            }
        })
    }

    private fun setupInputJob(amount: String): Job {
        var amountOk: Boolean
        return GlobalScope.launch (Dispatchers.Main) {

            delay(100)
            amountOk = true

            if (amount.isNotEmpty()) {
                amountOk = viewModel.validateAmount(amount.toFloat())
            }
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = Constants.EMPTY

            if (amountOk.not()) {
                textInputLayout.startAnimation(shake)
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = getString(R.string.error_amount_input)
            }
        }
    }
}
