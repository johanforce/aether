package com.jarvis.weatherj.presentation.base.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Bast List Adapter
 */
abstract class BaseListAdapter<T, VH : BaseItemViewHolder<T>>(
    diffUtilCallback: DiffUtil.ItemCallback<T> = BaseDiffUtilItemCallback()
) : ListAdapter<T, VH>(diffUtilCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(getItem(position))
    }

    /**
     * Submit list with DiffUtils
     */
    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(list: List<T>?) {
        if (list == currentList) {
            notifyDataSetChanged()
            return
        }
        super.submitList(list ?: emptyList())
    }

    protected fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int): View =
        LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)

    @SuppressLint("NotifyDataSetChanged")
    fun notifyList() {
        notifyDataSetChanged()
    }
}

abstract class BaseItemViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected val context: Context get() = itemView.context

    protected var itemData: T? = null

    open fun bindData(model: T) {
        itemData = model
    }

    open fun bindDataQuestion(model: T, isBottom: Boolean = false) {
        itemData = model
    }
}
