package com.example.ch1.section3_todo

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

class TodoDecoration(var itemList: MutableList<Item>): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        //항목의 index 획득..
        val index = parent.getChildAdapterPosition(view)
        val itemVO = itemList.get(index)
        if(itemVO.type == Item.TYPE_DATA){
            view.setBackgroundColor(0xFFFFFFFF.toInt())
            ViewCompat.setElevation(view, 10.0f)
        }
        outRect.set(20, 10, 20, 10)
    }
}