package technical.test.jetpack.utils.formatter

import technical.test.jetpack.utils.Constants

class AmountFormatter {

    companion object {

        fun prepareAmountToProcess(amount: String): String {
            return amount
                .replace(Constants.EURO_SYMBOL, Constants.EMPTY)
                .replace(Constants.SPACE, Constants.EMPTY)
                .trim()
        }

        fun formatAmount(amount: String): String {
            return amount + Constants.SPACE + Constants.EURO_SYMBOL
        }

        fun isAmountDecimal(amount: String): Boolean {
            return amount.contains(".") || amount.contains(",")
        }

        fun getAmountlengthMax(amount:String, lengthIntMax: Int, lengthDecimalMax: Int): Int {
            var lengthMax = lengthIntMax
            if(isAmountDecimal(amount) && checkIntAmountlengthMax(amount, lengthIntMax)) {
                lengthMax = amount.substringBefore(".").length + lengthDecimalMax
                if (amount.substringAfter(".").length <= 1) {
                    lengthMax--
                }
                ++lengthMax
            }
            return lengthMax
        }

        private fun checkIntAmountlengthMax(amount: String, lengthIntMax: Int): Boolean {
            return amount.substringBefore(".").length < lengthIntMax
        }

        fun validateAmountlengthInt(amount: String, lengthIntMax: Int): Boolean {
            return amount.length <= lengthIntMax
        }

        fun validateAmountlengthDecimal(amount:String, lengthIntMax: Int, lengthDecimalMax: Int): Boolean {
            val amountInt = amount.substringBefore(".").length < lengthIntMax
            val amountDecimal = amount.substringAfter(".").length <= lengthDecimalMax
            return amountInt && amountDecimal
        }
    }
}