package com.jarvis.weatherj.presentation.widget.listItem

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView

interface IListItem {
    fun setTitle(title: String?)
    val textViewTitle: TextView?
    fun setTitleVisible(isVisible: Boolean)
    fun setDescription(description: String?)
    val textViewDescription: TextView?
    fun setDescriptionVisible(isVisible: Boolean)
    fun setTextValueStyle1(value: String?)
    fun setTextValueStyle1Visible(isVisible: Boolean)
    val textViewValueStyle1: TextView?
    fun setTextValueStyle2(value: String?)
    fun setTextValueStyle2Visible(isVisible: Boolean)
    val textViewValueStyle2: TextView?
    fun setStartIcon(startIcon: Drawable?)
    fun setStartIconVisible(isVisible: Boolean)
    val imageViewStartIcon: ImageView?
    fun setEndIcon(endIcon: Drawable?)
    fun setEndIconVisible(isVisible: Boolean)
    val imageViewEndIcon: ImageView?
}
