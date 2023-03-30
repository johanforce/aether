@file:Suppress("DEPRECATION", "unused")

package com.jarvis.weatherj.presentation.widget.listItem

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jarvis.weatherj.databinding.*
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemEndElement
import com.jarvis.weatherj.presentation.widget.listItem.config.ListItemStartElement

class ListItemViewBinder(private val context: Context, private val parentView: ViewGroup) :
    IListItem {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val itemViewWrapper: ListItemViewWrapper = ListItemViewWrapper()
    private var bindingIconValue: ListItemIconValueBinding? = null
    private var binding2LineContentStyle1: ListItem2linecontentStyle1Binding? = null
    private var binding2LineContentStyle2: ListItem2linecontentStyle2Binding? = null
    private var binding2LineEndWithTextAndIcon: ListItem2linecontentEndTextIconBinding? = null
    private var listItemIconValueStyle3Binding: ListItemIconValueStyle3Binding? = null
    private var listItemBottomsheetDropdownDialogBinding: ListItemBottomsheetDropdownDialogBinding? =
        null

    fun bindViewIconValue(
        title: String?, startIconElement: Int, startIcon: Drawable?,
        endItemElement: Int, endIcon: Drawable?, endText: String?, endTextColor: Int
    ): ListItemIconValueBinding? {
        bindingIconValue = ListItemIconValueBinding.inflate(
            LayoutInflater.from(
                context
            ), parentView, true
        )
        bindingIconValue!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(bindingIconValue!!.tvTitle)
        if (startIconElement == ListItemStartElement.DISPLAY.value) {
            bindingIconValue!!.ivStartIcon.setImageDrawable(startIcon)
            itemViewWrapper.wrapStartIcon(bindingIconValue!!.ivStartIcon)
        }
        when (endItemElement) {
            ListItemEndElement.TEXT_VALUE_STYLE1.value -> {
                bindingIconValue!!.tvValue.text = endText
                bindingIconValue!!.tvValue.setTextColor(endTextColor)
                itemViewWrapper.wrapTvValueStyle1(bindingIconValue!!.tvValue)
            }
            ListItemEndElement.TEXT_AND_ICON.value -> {
                bindingIconValue!!.tvValue.text = endText
                itemViewWrapper.wrapTvValueStyle1(bindingIconValue!!.tvValue)
                itemViewWrapper.wrapIvValueIcon(bindingIconValue!!.ivValueIcon)
            }
            ListItemEndElement.ICON.value -> {
                bindingIconValue!!.ivEndIcon.setImageDrawable(endIcon)
                itemViewWrapper.wrapIvEndIcon(bindingIconValue!!.ivEndIcon)
            }
        }
        return bindingIconValue
    }

    fun bindView2LineContentStyle1(
        title: String?,
        description: String?,
        startIconElement: Int,
        startIcon: Drawable?,
        endElement: Int,
        endItemText: String?,
        endIcon: Drawable?
    ): ListItem2linecontentStyle1Binding? {
        binding2LineContentStyle1 =
            ListItem2linecontentStyle1Binding.inflate(inflater, parentView, true)
        binding2LineContentStyle1!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(binding2LineContentStyle1!!.tvTitle)
        binding2LineContentStyle1!!.tvDescription.text = description
        itemViewWrapper.wrapTvDescription(binding2LineContentStyle1!!.tvDescription)
        if (startIconElement == ListItemStartElement.DISPLAY.value) {
            binding2LineContentStyle1!!.ivStartIcon.setImageDrawable(startIcon)
            itemViewWrapper.wrapStartIcon(binding2LineContentStyle1!!.ivStartIcon)
        }
        if (endElement == ListItemEndElement.TEXT_VALUE_STYLE1.value) {
            binding2LineContentStyle1!!.tvValue.text = endItemText
            itemViewWrapper.wrapTvValueStyle1(binding2LineContentStyle1!!.tvValue)
        } else if (endElement == ListItemEndElement.ICON.value) {
            binding2LineContentStyle1!!.ivEndIcon.setImageDrawable(endIcon)
            itemViewWrapper.wrapIvEndIcon(binding2LineContentStyle1!!.ivEndIcon)
        }
        return binding2LineContentStyle1
    }

    fun bindView2LineContentStyle2(
        title: String?,
        description: String?,
        startIconElement: Int,
        startIcon: Drawable?,
        endElement: Int,
        endItemText: String?,
        endIconDraw: Drawable?
    ) {
        binding2LineContentStyle2 =
            ListItem2linecontentStyle2Binding.inflate(inflater, parentView, true)
        binding2LineContentStyle2!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(binding2LineContentStyle2!!.tvTitle)
        binding2LineContentStyle2!!.tvDescription.text = description
        itemViewWrapper.wrapTvDescription(binding2LineContentStyle2!!.tvDescription)
        if (startIconElement == ListItemStartElement.DISPLAY.value) {
            binding2LineContentStyle2!!.ivStartIcon.setImageDrawable(startIcon)
            itemViewWrapper.wrapStartIcon(binding2LineContentStyle2!!.ivStartIcon)
        }
        if (endElement == ListItemEndElement.TEXT_VALUE_STYLE2.value) {
            binding2LineContentStyle2!!.tvValue.text = endItemText
            itemViewWrapper.wrapTvValueStyle2(binding2LineContentStyle2!!.tvValue)
        } else if (endElement == ListItemEndElement.ICON.value) {
            itemViewWrapper.wrapIvEndIcon(binding2LineContentStyle2!!.ivEndIcon)
            binding2LineContentStyle2!!.ivEndIcon.setImageDrawable(endIconDraw)
        }
    }

    fun bindView2LineEndWithTextAndIcon(
        title: String?,
        description: String?,
        itemEndElement: Int,
        itemEndText: String?
    ): ListItem2linecontentEndTextIconBinding? {
        binding2LineEndWithTextAndIcon =
            ListItem2linecontentEndTextIconBinding.inflate(inflater, parentView, true)
        binding2LineEndWithTextAndIcon!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(binding2LineEndWithTextAndIcon!!.tvTitle)
        if (description == null) {
            binding2LineEndWithTextAndIcon!!.tvDescription.visibility = View.GONE
        } else {
            binding2LineEndWithTextAndIcon!!.tvDescription.text = description
            itemViewWrapper.wrapTvDescription(binding2LineEndWithTextAndIcon!!.tvDescription)
        }
        binding2LineEndWithTextAndIcon!!.tvValue.text = itemEndText
        itemViewWrapper.wrapTvValueStyle1(binding2LineEndWithTextAndIcon!!.tvValue)
        if (itemEndElement == ListItemEndElement.INVISIBLE.value) {
            binding2LineEndWithTextAndIcon!!.ivEndIcon.visibility = View.INVISIBLE
        } else {
            itemViewWrapper.wrapIvEndIcon(binding2LineEndWithTextAndIcon!!.ivEndIcon)
        }
        return binding2LineEndWithTextAndIcon
    }

    fun bindViewListItemIconValueStyle3(
        title: String?,
        description: String?,
        startIconElement: Int,
        startIcon: Drawable?,
        endItemElement: Int,
        endIcon: Drawable?
    ): ListItemIconValueStyle3Binding? {
        listItemIconValueStyle3Binding =
            ListItemIconValueStyle3Binding.inflate(inflater, parentView, true)
        listItemIconValueStyle3Binding!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(listItemIconValueStyle3Binding!!.tvTitle)
        listItemIconValueStyle3Binding!!.tvDescription.text = description
        itemViewWrapper.wrapTvDescription(listItemIconValueStyle3Binding!!.tvDescription)
        if (startIconElement == ListItemStartElement.DISPLAY.value) {
            listItemIconValueStyle3Binding!!.ivStartIcon.setImageDrawable(startIcon)
            itemViewWrapper.wrapStartIcon(listItemIconValueStyle3Binding!!.ivStartIcon)
        }
        if (endItemElement == ListItemEndElement.HIDE.value) {
            listItemIconValueStyle3Binding!!.ivEndIcon.visibility = View.GONE
        } else {
            itemViewWrapper.wrapIvEndIcon(listItemIconValueStyle3Binding!!.ivEndIcon)
            listItemIconValueStyle3Binding!!.ivEndIcon.setImageDrawable(endIcon)
        }
        return listItemIconValueStyle3Binding
    }

    fun bindViewBottomSheetDropdownDialog(
        title: String?,
        startIconElement: Int,
        startIcon: Drawable?,
        endItemElement: Int,
        endIcon: Drawable?
    ): ListItemIconValueBinding? {
        listItemBottomsheetDropdownDialogBinding = ListItemBottomsheetDropdownDialogBinding.inflate(
            LayoutInflater.from(
                context
            ), parentView, true
        )
        listItemBottomsheetDropdownDialogBinding!!.tvTitle.text = title
        itemViewWrapper.wrapTvTitle(listItemBottomsheetDropdownDialogBinding!!.tvTitle)
        if (startIconElement == ListItemStartElement.DISPLAY.value) {
            listItemBottomsheetDropdownDialogBinding!!.ivStartIcon.setImageDrawable(startIcon)
            itemViewWrapper.wrapStartIcon(listItemBottomsheetDropdownDialogBinding!!.ivStartIcon)
        }
        if (endItemElement == ListItemEndElement.ICON.value) {
            listItemBottomsheetDropdownDialogBinding!!.ivEndIcon.setImageDrawable(endIcon)
            itemViewWrapper.wrapIvEndIcon(listItemBottomsheetDropdownDialogBinding!!.ivEndIcon)
        }
        return bindingIconValue
    }

    override fun setTitle(title: String?) {
        if (itemViewWrapper.tvTitle != null) {
            itemViewWrapper.tvTitle?.text = title
        }
    }

    override fun setTitleVisible(isVisible: Boolean) {
        if (itemViewWrapper.tvTitle != null) {
            itemViewWrapper.tvTitle?.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override fun setDescription(description: String?) {
        if (itemViewWrapper.tvDescription != null) {
            itemViewWrapper.tvDescription?.text = Html.fromHtml(description)
        }
    }

    override fun setDescriptionVisible(isVisible: Boolean) {
        if (itemViewWrapper.tvDescription != null) {
            itemViewWrapper.tvDescription?.visibility =
                if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override fun setTextValueStyle1(value: String?) {
        if (itemViewWrapper.tvValueStyle1 != null) {
            itemViewWrapper.tvValueStyle1?.text = value
        }
    }

    override fun setTextValueStyle1Visible(isVisible: Boolean) {
        if (itemViewWrapper.tvValueStyle1 != null) {
            itemViewWrapper.tvValueStyle1?.visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override fun setTextValueStyle2(value: String?) {
        if (itemViewWrapper.tvValueStyle2 != null) {
            itemViewWrapper.tvValueStyle2?.text = value
        }
    }

    override fun setTextValueStyle2Visible(isVisible: Boolean) {
        if (itemViewWrapper.tvValueStyle2 != null) {
            itemViewWrapper.tvValueStyle2?.visibility =
                if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override fun setStartIcon(startIcon: Drawable?) {
        if (itemViewWrapper.ivStartIcon != null) {
            itemViewWrapper.ivStartIcon?.setImageDrawable(startIcon)
        }
    }

    override fun setStartIconVisible(isVisible: Boolean) {
        if (itemViewWrapper.ivStartIcon != null) {
            itemViewWrapper.ivStartIcon?.visibility =
                if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override fun setEndIcon(endIcon: Drawable?) {
        if (itemViewWrapper.ivEndIcon != null) {
            itemViewWrapper.ivEndIcon?.setImageDrawable(endIcon)
        }
    }

    override fun setEndIconVisible(isVisible: Boolean) {
        if (itemViewWrapper.ivEndIcon != null) {
            itemViewWrapper.ivEndIcon?.visibility =
                if (isVisible) View.VISIBLE else View.GONE
        }
    }

    override val textViewTitle: TextView?
        get() = itemViewWrapper.tvTitle
    override val textViewDescription: TextView?
        get() = itemViewWrapper.tvDescription
    override val textViewValueStyle1: TextView?
        get() = itemViewWrapper.tvValueStyle1
    override val textViewValueStyle2: TextView?
        get() = itemViewWrapper.tvValueStyle2
    override val imageViewStartIcon: ImageView?
        get() = itemViewWrapper.ivStartIcon
    override val imageViewEndIcon: ImageView?
        get() = itemViewWrapper.ivEndIcon
}