@file:Suppress("unused")

package com.jarvis.weatherj.common

import android.os.SystemClock
import android.text.SpannableString
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

private var lastTimeClicked: Long = 0
private var lastTimeClickedId = 0

/**
 * This is method show in textView
 * @param visible is type View
 */
fun View.show(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.inVisibility(visible: Boolean) {
    if (visible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun View.click(defaultInterval: Int = 1000, onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener(defaultInterval) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {

    override fun onClick(v: View) {
        if (lastTimeClickedId == v.id) {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
                return
            }
        } else if (SystemClock.elapsedRealtime() - lastTimeClicked < 200) {
            return
        }

        lastTimeClicked = SystemClock.elapsedRealtime()
        lastTimeClickedId = v.id
        onSafeCLick(v)
    }
}

/**
 * This is method set text in textView
 * @param text is type View
 */
fun TextView.setTextNullable(text: Any?) {
    when (text) {
        // if text is String return it or empty
        is String -> this.text = text as String? ?: ""
        // if text is SpannableString return it or empty
        is SpannableString -> this.text = text as SpannableString? ?: ""
        // else return empty
        else -> this.text = ""
    }
}

fun ImageView.setTintColor(@ColorRes colorRes: Int) {
    setColorFilter(ContextCompat.getColor(context, colorRes))
}

fun View.setBackgroundTintColor(@ColorRes colorRes: Int) {
    backgroundTintList =
        ContextCompat.getColorStateList(context, colorRes)
}
