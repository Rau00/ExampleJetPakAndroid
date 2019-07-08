package technical.test.jetpack.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.navigation_component.view.*
import technical.test.jetpack.R

class NavigationComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var hasNext = true
    private var hasBack = true

    init {
        LayoutInflater.from(context).inflate(R.layout.navigation_component, this, true)

        attrs?.let {

            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.navigationComponentAttributes, 0, 0
            )
            hasNext = typedArray.getBoolean(R.styleable.navigationComponentAttributes_hasNext, true)
            hasBack = typedArray.getBoolean(R.styleable.navigationComponentAttributes_hasBack, true)

            if(hasNext.not())
                fabNext.hide()

            if (hasBack.not())
                fabBack.hide()
        }
    }

    fun actionNext(action: () -> Unit) {
        fabNext.setOnClickListener { action() }
    }

    fun actionBack(action: () -> Unit) {
        fabBack.setOnClickListener { action() }
    }
}