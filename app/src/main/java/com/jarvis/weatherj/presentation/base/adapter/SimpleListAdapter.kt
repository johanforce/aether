package com.jarvis.weatherj.presentation.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.jarvis.weatherj.common.click

/**
 * Simple List Adapter
 * Bind simple data with Single View Holder type
 * @sample
 * <pre>
 * val adapter = SimpleListAdapter<Profile>(R.layout.item_profile) { itemView, profile, position ->
 *      itemView.textFirstName.text = profile.firstName
 *      itemView.textLastName.text = profile.lastName
 *      itemView.viewLineBottom.isInvisible = position == profiles.size -1 // not show bottom line if item is last
 * }
 * adapter.onItemClick = { itemView, profile, position ->
 *     // handle click item event
 * }
 * recyclerView.adapter = adapter // set adapter
 * adapter.submitList(profiles) // update data
 *
 * </pre>
 * @param itemLayoutResId : Layout resource id of item
 * @param onBind : High-order function, call when bind data into itemView
 * @param T: type of item's data
 * @author huytq
 */
class SimpleListAdapter<T>(
    @LayoutRes
    private val itemLayoutResId: Int,
    var onBind: (View, T, Int) -> Unit = { _, _, _ -> },
) : BaseListAdapter<T, BaseItemViewHolder<T>>() {
    var delayClick = 500
    var onItemClick: (View, T, Int) -> Unit = { _, _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder<T> {
        return object : BaseItemViewHolder<T>(inflateView(parent, itemLayoutResId)) {
            init {
                itemView.click(delayClick) {
                    itemData?.let {
                        onItemClick(itemView, it, absoluteAdapterPosition)
                    }
                }
            }

            override fun bindData(model: T) {
                super.bindData(model)
                onBind(itemView, model, absoluteAdapterPosition)
            }
        }
    }
}
