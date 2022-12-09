package com.jarvis.weatherj.presentation.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Diff Util for RecyclerView.Adapter
 */
@Suppress("WRONG_TYPE_PARAMETER_NULLABILITY_FOR_JAVA_OVERRIDE")
open class BaseDiffUtilItemCallback<T> : DiffUtil.ItemCallback<T>() {

    /**
     * Compare 2 item is the same
     * default is compare by reference
     * If item has id, overridable compare by id
     */
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        // Override this if your item have an id
        return oldItem === newItem
    }

    /**
     * 2 ways to make this happened
     *  - Simple way: make your [T] item is data class
     *  - Performance way: make your own [T] item's equals() & hashcode() methods
     */
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}
