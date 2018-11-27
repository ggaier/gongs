package com.github.ggaier.gongs.ui.view

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wenbo, 2018/11/27
 */
data class ViewState(
    override val id: String,
    private val recyclerView: RecyclerView
) : State{

    val leftPosition: Int
    val leftOffset: Int

    init {
        val lm = recyclerView.layoutManager
        val (position, offset) = if (lm is androidx.recyclerview.widget.LinearLayoutManager) {
            /* To get rid of Parcelable, https://stackoverflow.com/a/35287828/4894238 */
            val item = recyclerView.getChildAt(0)
            val leftOffset = if (item == null) 0 else item.left - recyclerView.paddingLeft
            lm.findFirstVisibleItemPosition() to leftOffset
        } else {
            0 to 0
        }
        leftPosition = position
        leftOffset = offset
    }
}