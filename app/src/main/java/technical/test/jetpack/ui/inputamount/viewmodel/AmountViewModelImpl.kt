package technical.test.jetpack.ui.inputamount.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.android.ext.android.inject
import technical.test.jetpack.ui.interfacebase.NavigationView
import technical.test.jetpack.ui.inputamount.router.AmountRouter
import technical.test.jetpack.ui.inputamount.view.AmountView
import technical.test.jetpack.utils.Constants
import technical.test.jetpack.utils.formatter.AmountFormatter

class AmountViewModelImpl(private val amountView: AmountView): AmountViewModel, ViewModel() {
    val AMOUNT_MAX = 1000F
    val AMOUNT_length_MAX_INT = 4
    val AMOUNT_length_MAX_DECIMAL = 2
    val INIT_AMOUNT = "0"

    val router: AmountRouter by (amountView as Fragment).inject()

    override var amount: String = INIT_AMOUNT
    override var finalAmount: MutableLiveData<String> = MutableLiveData()

    override fun initView(amountView: AmountView) {
        router.setNavigationView((amountView as Fragment).activity as NavigationView)
        finalAmount.value = INIT_AMOUNT
    }

    override fun prepareAmountToProcess(amount: String) {
        this.amount = AmountFormatter.prepareAmountToProcess(amount)
    }

    override fun validateAmount(amount: Float): Boolean {
        return amount <= AMOUNT_MAX
    }

    override fun validateAmountlength(): Boolean {
        return if (isAmountDecimal()) {
            AmountFormatter.validateAmountlengthDecimal(this.amount, AMOUNT_length_MAX_INT, AMOUNT_length_MAX_DECIMAL)
        } else {
            AmountFormatter.validateAmountlengthInt(this.amount, AMOUNT_length_MAX_INT)
        }
    }

    override fun formatAmount(): String {
        return AmountFormatter.formatAmount(amount)
    }

    override fun isAmountDecimal(): Boolean {
        return AmountFormatter.isAmountDecimal(amount)
    }

    override fun getAmountlengthMax(): Int {
        return AmountFormatter.getAmountlengthMax(amount, AMOUNT_length_MAX_INT, AMOUNT_length_MAX_DECIMAL)
    }

    override fun updateInputLength() {
        amount = amount.substring(0, getAmountlengthMax())
    }

    override fun nextStep() {
        val bundle = Bundle()
        bundle.putString(Constants.AMOUNT, finalAmount.value)
        router.setBundleInfo(Constants.AMOUNT, bundle)
        router.nextScreen()
    }

    override fun backStep() {
        router.backScreen()
    }
}
