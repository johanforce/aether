package com.jarvis.weatherj.presentation.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jarvis.weatherj.R
import com.jarvis.weatherj.common.click
import com.jarvis.weatherj.databinding.ToolbarBinding

class ViewToolbar : FrameLayout {
    private var binding: ToolbarBinding? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun init(context: Context, attrs: AttributeSet?) {
        val systemService =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ToolbarBinding.inflate(systemService, this, true)

        if (attrs != null) {
            val attributeArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.JxToolbar)
            val toolbarTitle =
                attributeArray.getString(R.styleable.JxToolbar_toolbarTitle)
            binding?.tvTitle?.text = toolbarTitle
        }
    }

    fun backToolbar(onBackPress: () -> Unit = {}) {
        binding?.ivBack?.click {
            onBackPress()
        }
    }

    fun setTitleToolbar(text: String) {
        binding?.tvTitle?.text = text
    }
}