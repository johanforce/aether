package com.jarvis.weatherj.presentation.widget.listItem

import android.view.View
import android.widget.ImageView
import android.widget.TextView

class ListItemViewWrapper {
    var ivStartIcon: ImageView? = null
    var tvTitle: TextView? = null
    var tvValueStyle1: TextView? = null
    var tvValueStyle2: TextView? = null
    var tvDescription: TextView? = null
    var ivEndIcon: ImageView? = null
    var ivValueIcon: ImageView? = null
    fun wrapStartIcon(ivStartIcon: ImageView?) {
        this.ivStartIcon = ivStartIcon
        this.ivStartIcon!!.visibility = View.VISIBLE
    }

    fun wrapTvTitle(tvTitle: TextView?) {
        this.tvTitle = tvTitle
        this.tvTitle!!.visibility = View.VISIBLE
    }

    fun wrapTvValueStyle1(tvValueStyle1: TextView?) {
        this.tvValueStyle1 = tvValueStyle1
        this.tvValueStyle1!!.visibility = View.VISIBLE
    }

    fun wrapTvValueStyle2(tvValueStyle2: TextView?) {
        this.tvValueStyle2 = tvValueStyle2
        this.tvValueStyle2!!.visibility = View.VISIBLE
    }

    fun wrapTvDescription(tvDescription: TextView?) {
        this.tvDescription = tvDescription
        this.tvDescription!!.visibility = View.VISIBLE
    }

    fun wrapIvEndIcon(ivEndIcon: ImageView?) {
        this.ivEndIcon = ivEndIcon
        this.ivEndIcon!!.visibility = View.VISIBLE
    }

    fun wrapIvValueIcon(ivValueIcon: ImageView?) {
        this.ivValueIcon = ivValueIcon
        this.ivValueIcon!!.visibility = View.VISIBLE
    }

}