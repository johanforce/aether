@file:Suppress("DEPRECATION")

package com.jarvis.weatherj.presentation.widget.listItem

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.jarvis.weatherj.R
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemContentElement
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemEndElement
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemStartElement
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemEndButtonStyle
import com.jarvis.weatherj.presentation.widget.listItem.config.ListIteamEndTextColor
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemEndToggelStyle

open class JxListItem : RelativeLayout {
    private var attributeArray: TypedArray? = null
    private var itemStartElement = 0
    private var itemContentElement = 0
    private var itemEndElement = 0
    private var itemEndButtonStyle = 0
    private var itemEndToggleStyle = 0
    private var itemStartIcon: Drawable? = null
    private var itemEndIcon: Drawable? = null
    private var itemTitle: String? = null
    private var itemDescription: String? = null
    private var itemEndText: String? = null
    private var itemEndButtonText: String? = null
    private var itemEndTextColor = 0
    var viewBinder: ListItemViewBinder? = null

    constructor(context: Context) : super(context) {
        initView(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        try {
            attributeArray = context.obtainStyledAttributes(attrs, R.styleable.JxListItem)
            itemStartElement = attributeArray!!.getInt(
                R.styleable.JxListItem_itemStartElement,
                ListItemStartElement.HIDE.value
            )
            itemEndElement = attributeArray!!.getInt(
                R.styleable.JxListItem_itemEndElement,
                ListItemEndElement.HIDE.value
            )
            itemContentElement = attributeArray!!.getInt(
                R.styleable.JxListItem_itemContentElement,
                ListItemContentElement.ONLY_TITLE.value
            )
            itemEndButtonStyle = attributeArray!!.getInt(
                R.styleable.JxListItem_itemEndButtonStyle,
                ListItemEndButtonStyle.PRIMARY.value
            )
            itemEndTextColor = attributeArray!!.getColor(
                R.styleable.JxListItem_itemEndTextColor,
                resources.getColor(ListIteamEndTextColor.BASE_INK_500.value)
            )
            itemEndToggleStyle = attributeArray!!.getColor(
                R.styleable.JxListItem_iteamEndToggleStyle,
                ListItemEndToggelStyle.TOGGEL.value
            )
            itemTitle = attributeArray!!.getString(R.styleable.JxListItem_itemTitle)
            itemDescription = attributeArray!!.getString(R.styleable.JxListItem_itemDescription)
            itemEndText = attributeArray!!.getString(R.styleable.JxListItem_itemEndText)
            itemEndButtonText = attributeArray!!.getString(R.styleable.JxListItem_itemEndButtonText)
            itemStartIcon = attributeArray!!.getDrawable(R.styleable.JxListItem_itemStartIcon)
            itemEndIcon = attributeArray!!.getDrawable(R.styleable.JxListItem_itemEndIcon)
            viewBinder = ListItemViewBinder(context, this)
            inflateViewData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun inflateViewData() {
        when (itemContentElement) {
            ListItemContentElement.TITLE_AND_DESCRIPTION_STYLE1.value -> {
                viewBinder!!.bindView2LineContentStyle1(
                    itemTitle,
                    itemDescription,
                    itemStartElement,
                    itemStartIcon,
                    itemEndElement,
                    itemEndText,
                    itemEndIcon
                )
            }
            ListItemContentElement.TITLE_AND_DESCRIPTION_STYLE2.value -> {
                viewBinder!!.bindView2LineContentStyle2(
                    itemTitle,
                    itemDescription,
                    itemStartElement,
                    itemStartIcon,
                    itemEndElement,
                    itemEndText,
                    itemEndIcon
                )
            }
            ListItemContentElement.TITLE_AND_DESCRIPTION_STYLE3.value -> {
                viewBinder!!.bindViewListItemIconValueStyle3(
                    itemTitle,
                    itemDescription,
                    itemStartElement,
                    itemStartIcon,
                    itemEndElement,
                    itemEndIcon
                )
            }
            ListItemContentElement.TITLE_AND_DESCRIPTION_END_WITH_TEXT_AND_ICON.value -> {
                viewBinder!!.bindView2LineEndWithTextAndIcon(
                    itemTitle,
                    itemDescription,
                    itemEndElement,
                    itemEndText
                )
            }
            ListItemContentElement.BOTTOMSHEET_DROPDOWN_DIALOG.value -> {
                viewBinder!!.bindViewBottomSheetDropdownDialog(
                    itemTitle,
                    itemStartElement,
                    itemStartIcon,
                    itemEndElement,
                    itemEndIcon
                )
            }
            else -> {
                viewBinder!!.bindViewIconValue(
                    itemTitle,
                    itemStartElement,
                    itemStartIcon,
                    itemEndElement,
                    itemEndIcon,
                    itemEndText,
                    itemEndTextColor
                )
            }
        }
    }
}
