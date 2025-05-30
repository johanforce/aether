/*
 * Copyright © OMRON HEALTHCARE Co., Ltd. 2020. All rights reserved.
 */

@file:Suppress("unused", "DEPRECATION", "UNUSED_PARAMETER")

package com.jarvis.weatherj.presentation.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import com.jarvis.weatherj.R
import kotlin.math.round

class AdvanceDrawerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : DrawerLayout(context, attrs, defStyle) {

    var settings = HashMap<Int, Setting?>()
    private var defaultScrimColor = -0x67000000
    private var defaultDrawerElevation = 0f
    private var frameLayout: FrameLayout? = null
    var drawerView: View? = null
    private var statusBarColor = 0
    private var defaultFitsSystemWindows = false
    private var contrastThreshold = 3f
    private var cardBackgroundColor = 0

    var onDrawerOpened: () -> Unit = {}
    var onDrawerClosed: () -> Unit = {}

    init {
        init(context, attrs)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.advDrawerLayout)
        cardBackgroundColor = a.getColor(R.styleable.advDrawerLayout_adl_cardBackgroundColor, 0)
        a.recycle()
        defaultDrawerElevation = drawerElevation
        defaultFitsSystemWindows = fitsSystemWindows
        if (!isInEditMode) {
            statusBarColor = activity!!.window.statusBarColor
        }
        addDrawerListener(object : DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                this@AdvanceDrawerLayout.drawerView = drawerView
                updateSlideOffset(drawerView, slideOffset)
            }

            override fun onDrawerOpened(drawerView: View) {
                onDrawerOpened()
            }

            override fun onDrawerClosed(drawerView: View) {
                onDrawerClosed()
            }

            override fun onDrawerStateChanged(newState: Int) {
                // Do Nothing
            }
        })
        frameLayout = FrameLayout(context)
        frameLayout!!.setPadding(0, 0, 0, 0)
        super.addView(frameLayout)
    }

    override fun addView(child: View, params: ViewGroup.LayoutParams) {
        child.layoutParams = params
        addView(child)
    }

    override fun addView(child: View) {
        if (child is NavigationView) {
            super.addView(child)
        } else {
            val cardView = CardView(context)
            cardView.radius = 0f
            cardView.addView(child)
            cardView.cardElevation = 0f
            cardView.setCardBackgroundColor(cardBackgroundColor)
            frameLayout?.addView(cardView)
        }
    }

    fun setViewScale(gravity: Int, percentage: Float) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        val setting: Setting?
        if (!settings.containsKey(absGravity)) {
            setting = createSetting()
            settings[absGravity] = setting
        } else setting = settings[absGravity]
        setting?.percentage = percentage
        if (percentage < 1) {
            setStatusBarBackground(null)
            systemUiVisibility = 0
        }
        setting?.scrimColor = Color.TRANSPARENT
        setting?.drawerElevation = 0f
    }

    fun setViewElevation(gravity: Int, elevation: Float) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        val setting: Setting?
        if (!settings.containsKey(absGravity)) {
            setting = createSetting()
            settings[absGravity] = setting
        } else setting = settings[absGravity]
        setting?.scrimColor = Color.TRANSPARENT
        setting?.drawerElevation = 0f
        setting?.elevation = elevation
    }

    fun setViewScrimColor(gravity: Int, scrimColor: Int) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        val setting: Setting?
        if (!settings.containsKey(absGravity)) {
            setting = createSetting()
            settings[absGravity] = setting
        } else setting = settings[absGravity]
        setting?.scrimColor = scrimColor
    }

    fun setCardBackgroundColor(gravity: Int, color: Int) {
        cardBackgroundColor = color
        for (i in 0 until frameLayout!!.childCount) {
            val child = frameLayout!!.getChildAt(i) as CardView
            child.setCardBackgroundColor(cardBackgroundColor)
        }
    }

    fun setRadius(gravity: Int, radius: Float) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        val setting: Setting?
        if (!settings.containsKey(absGravity)) {
            setting = createSetting()
            settings[absGravity] = setting
        } else setting = settings[absGravity]
        setting!!.radius = radius
    }

    fun getSetting(gravity: Int): Setting? {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        return settings[absGravity]
    }

    override fun setDrawerElevation(elevation: Float) {
        defaultDrawerElevation = elevation
        super.setDrawerElevation(elevation)
    }

    override fun setScrimColor(@ColorInt color: Int) {
        defaultScrimColor = color
        super.setScrimColor(color)
    }

    fun useCustomBehavior(gravity: Int) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        if (!settings.containsKey(absGravity)) {
            val setting = createSetting()
            settings[absGravity] = setting
        }
    }

    fun removeCustomBehavior(gravity: Int) {
        val absGravity = getDrawerViewAbsoluteGravity(gravity)
        if (settings.containsKey(absGravity)) {
            settings.remove(absGravity)
        }
    }

    override fun openDrawer(drawerView: View, animate: Boolean) {
        super.openDrawer(drawerView, animate)
        post { updateSlideOffset(drawerView, if (isDrawerOpen(drawerView)) 1f else 0f) }
    }

    private fun updateSlideOffset(drawerView: View, slideOffset: Float) {
        val absHorizGravity = getDrawerViewAbsoluteGravity(GravityCompat.START)
        val childAbsGravity = getDrawerViewAbsoluteGravity(drawerView)
        val activity = activity
        val window = activity!!.window
        val isRtl: Boolean = layoutDirection == View.LAYOUT_DIRECTION_RTL
        for (i in 0 until frameLayout!!.childCount) {
            val child = frameLayout!!.getChildAt(i) as CardView
            val setting = settings[childAbsGravity]
            var adjust: Float
            if (setting != null) {
                setupDataUpdateSlideOffset(setting, slideOffset, window)

                child.radius = (round((setting.radius * slideOffset) * 2.0) / 2).toFloat()
                super.setScrimColor(setting.scrimColor)
                super.setDrawerElevation(setting.drawerElevation)
                val percentage = 1f - setting.percentage
                ViewCompat.setScaleY(child, 1f - percentage * slideOffset)
                child.cardElevation = setting.elevation * slideOffset
                adjust = setting.elevation
                val isLeftDrawer: Boolean =
                    if (isRtl) childAbsGravity != absHorizGravity else childAbsGravity == absHorizGravity
                val width =
                    if (isLeftDrawer) drawerView.width + adjust else -drawerView.width - adjust
                updateSlideOffset(child, width, slideOffset)
            } else {
                super.setScrimColor(defaultScrimColor)
                super.setDrawerElevation(defaultDrawerElevation)
            }
        }
    }

    private fun setupDataUpdateSlideOffset(
        setting: Setting,
        slideOffset: Float,
        window: Window
    ) {
        if (setting.percentage < 1.0) {
            if (drawerView?.background is ColorDrawable) {
                val color = ColorUtils.setAlphaComponent(
                    statusBarColor,
                    (255 - 255 * slideOffset).toInt()
                )
                window.statusBarColor = color
                val bgColor = (drawerView?.background as ColorDrawable).color
                window.decorView.setBackgroundColor(bgColor)
                systemUiVisibility = if (ColorUtils.calculateContrast(
                        Color.WHITE,
                        bgColor
                    ) < contrastThreshold && slideOffset > 0.4
                ) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
            } else if (drawerView?.background is MaterialShapeDrawable &&
                (drawerView?.background as MaterialShapeDrawable).fillColor != null
            ) {
                val color = ColorUtils.setAlphaComponent(
                    statusBarColor,
                    (255 - 255 * slideOffset).toInt()
                )
                window.statusBarColor = color
                val bgColor =
                    (drawerView?.background as MaterialShapeDrawable).fillColor!!.defaultColor
                window.decorView.setBackgroundColor(bgColor)
                systemUiVisibility = if (ColorUtils.calculateContrast(
                        Color.WHITE,
                        bgColor
                    ) < contrastThreshold && slideOffset > 0.4
                ) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
            }
        }
    }

    fun setContrastThreshold(contrastThreshold: Float) {
        this.contrastThreshold = contrastThreshold
    }

    val activity: Activity?
        get() = getActivity(context)

    private fun getActivity(context: Context?): Activity? {
        if (context == null) return null
        if (context is Activity) return context
        return if (context is ContextWrapper) getActivity(context.baseContext) else null
    }

    private fun updateSlideOffset(
        child: CardView,
        width: Float,
        slideOffset: Float
    ) {
        child.x = width * slideOffset
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerView?.let {
            updateSlideOffset(it, if (isDrawerOpen(it)) 1f else 0f)
        }
    }

    private fun getDrawerViewAbsoluteGravity(gravity: Int): Int {
        return GravityCompat.getAbsoluteGravity(
            gravity,
            ViewCompat.getLayoutDirection(this)
        ) and GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK
    }

    private fun getDrawerViewAbsoluteGravity(drawerView: View): Int {
        val gravity = (drawerView.layoutParams as LayoutParams).gravity
        return getDrawerViewAbsoluteGravity(gravity)
    }

    private fun createSetting(): Setting {
        return Setting()
    }

    open inner class Setting {
        var fitsSystemWindows = false
        var percentage = 1f
        var scrimColor = defaultScrimColor
        var elevation = 0f
        var drawerElevation = defaultDrawerElevation
        var radius = 0f
    }

    companion object {
        private val TAG = AdvanceDrawerLayout::class.java.simpleName
    }
}
