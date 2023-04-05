package com.ahmadhamwi.tabsync_compose

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.findFirstFullyVisibleItemIndex(): Int = findFullyVisibleItemIndex(reversed = true)

fun LazyListState.findLastFullyVisibleItemIndex(): Int = findFullyVisibleItemIndex(reversed = false)

fun LazyListState.findFullyVisibleItemIndex(reversed: Boolean): Int {
    layoutInfo.visibleItemsInfo.run { if (reversed) reversed() else this }.forEach { itemInfo ->
        val itemStartOffset = itemInfo.offset
        val itemEndOffset = itemInfo.offset + itemInfo.size
        val viewportStartOffset = layoutInfo.viewportStartOffset
        val viewportEndOffset = layoutInfo.viewportEndOffset
        if (itemStartOffset >= viewportStartOffset && itemEndOffset <= viewportEndOffset) {
            return itemInfo.index
        }
    }
    return -1
}
