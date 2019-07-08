package technical.test.jetpack.ui.inputamount.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations
import technical.test.jetpack.ui.inputamount.view.AmountFragment

class AmountViewModelImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var amountView = AmountFragment.newInstance()
    var amountViewModel: AmountViewModelImpl = AmountViewModelImpl(amountView)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        amountViewModel.amount = "23.3"
    }

    @Test
    fun prepareAmountToProcess() {
    }

    @Test
    fun validateAmount() {
    }

    @Test
    fun validateAmountlength_correct() {
        assertEquals(true, amountViewModel.validateAmountlength())
    }

    @Test
    fun validateAmountlength_incorrect() {
        amountViewModel.amount = "23.334"
        assertEquals(false, amountViewModel.validateAmountlength())
    }

    @Test
    fun validateAmountlength_incorrect_integer() {
        amountViewModel.amount = "1000."
        assertEquals(false, amountViewModel.validateAmountlength())
    }
    @Test
    fun validateAmountlength_correct_integer() {
        amountViewModel.amount = "100."
        assertEquals(true, (amountViewModel.validateAmountlength()))
    }

    @Test
    fun formatAmount() {
    }

    @Test
    fun isAmountDecimal() {
    }

    @Test
    fun getAmountlengthMax() {
        amountViewModel.amount = "1000"
        assertEquals(4, amountViewModel.getAmountlengthMax())
    }

    @Test
    fun updateInputLength() {
    }
}