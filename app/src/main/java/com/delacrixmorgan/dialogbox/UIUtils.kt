package com.delacrixmorgan.dialogbox

import android.content.Context
import android.view.View
import android.view.View.MeasureSpec
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListAdapter
import android.widget.ListPopupWindow
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

object UIUtils {
    fun getPlainListPopUpWindow(
        context: Context,
        items: List<String> = ArrayList(),
        anchor: View,
        @DrawableRes backgroundDrawableRes: Int = 0,
        @LayoutRes cellLayoutRes: Int = android.R.layout.simple_list_item_1,
        horizontalOffsetValue: Int = 0,
        verticalOffsetValue: Int = 0
    ): ListPopupWindow {
        val adapter: ArrayAdapter<String> = ArrayAdapter(context, cellLayoutRes, items)
        val listPopupWindow = ListPopupWindow(context)

        listPopupWindow.apply {
            setAdapter(adapter)

            width = measureContentWidth(context, adapter)
            height = ListPopupWindow.WRAP_CONTENT
            isModal = true
            anchorView = anchor
            horizontalOffset = horizontalOffsetValue
            verticalOffset = verticalOffsetValue

            if (backgroundDrawableRes != 0) {
                val drawable = ContextCompat.getDrawable(context, backgroundDrawableRes)
                setBackgroundDrawable(drawable)
            }
        }

        return listPopupWindow
    }

    private fun measureContentWidth(context: Context, adapter: ListAdapter): Int {
        val measureParentViewGroup = FrameLayout(context)
        var itemView: View? = null

        var maxWidth = 0
        var itemType = 0

        val widthMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        for (index in 0 until adapter.count) {
            val positionType = adapter.getItemViewType(index)
            if (positionType != itemType) {
                itemType = positionType
                itemView = null
            }
            itemView = adapter.getView(index, itemView, measureParentViewGroup)
            itemView.measure(widthMeasureSpec, heightMeasureSpec)
            val itemWidth = itemView.measuredWidth
            if (itemWidth > maxWidth) {
                maxWidth = itemWidth
            }
        }
        return maxWidth
    }
}