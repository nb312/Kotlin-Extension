package com.nb.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nb.nbContext


/**
 * Created by NieBin on 2018-07-31
 * Github: https://github.com/nb312
 * Email: niebin312@gmail.com
 * RecyclerView 的扩展
 */
private const val HIGH = 1
private const val WIDTH = 1
fun RecyclerView.verticalLine(height: Int, isStack: Boolean = true) {
    var linear = LinearLayoutManager(nbContext, LinearLayoutManager.VERTICAL, false)
    linear.stackFromEnd = isStack
    this.layoutManager = linear

    var line = NBItemLine()
    line.verticalSize = height * HIGH
    this.addItemDecoration(line)
}

/**the direction is horizontal
 * @param width the width of the gap between two items.
 * */
fun RecyclerView.horizontalLine(width: Int) {
    this.layoutManager = LinearLayoutManager(nbContext, LinearLayoutManager.HORIZONTAL, false)
    var line = NBItemLine()
    line.orientation = NBItemLine.DIVIDER_HORIZONTAL
    line.horizontalSize = width * WIDTH
    this.addItemDecoration(line)
}

fun RecyclerView.verticalLine(width: Int, num: Int) {
    this.layoutManager = GridLayoutManager(nbContext, num, LinearLayoutManager.VERTICAL, false)
    var line = NBItemLine()
    line.orientation = NBItemLine.DIVIDER_VERTICAL
    line.verticalSize = width * HIGH
    this.addItemDecoration(line)
}

/**
 * jump to the top item of list.
 * */
fun RecyclerView.scroll2Top() {
    scrollToPosition(0)
    if (layoutManager is LinearLayoutManager) {
        (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 0)
    }
}