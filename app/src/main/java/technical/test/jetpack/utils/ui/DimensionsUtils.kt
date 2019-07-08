package technical.test.jetpack.utils.ui

import android.content.Context
import android.util.TypedValue

class DimensionsUtils {

    companion object {

        fun dpToPixels(ctx: Context, dipValue: Float): Int {
            val metrics = ctx.getResources().getDisplayMetrics()
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics).toInt()
        }
    }
}