package technical.test.jetpack.ui.inputamount.viewmodel

import androidx.lifecycle.MutableLiveData
import technical.test.jetpack.ui.inputamount.view.AmountView

interface AmountViewModel {
    var amount: String
    var finalAmount: MutableLiveData<String>

    fun initView(amountView: AmountView)
    fun validateAmount(amount: Float): Boolean
    fun validateAmountlength(): Boolean
    fun formatAmount(): String
    fun isAmountDecimal(): Boolean
    fun getAmountlengthMax(): Int
    fun prepareAmountToProcess(amount: String)
    fun updateInputLength()
    fun nextStep()
    fun backStep()
}