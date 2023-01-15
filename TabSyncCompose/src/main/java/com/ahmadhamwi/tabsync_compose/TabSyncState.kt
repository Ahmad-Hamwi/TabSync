package com.ahmadhamwi.tabsync_compose

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Stable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class TabSyncState(
    var selectedTabIndex: Int,
    var lazyListState: LazyListState,
    private var coroutineScope: CoroutineScope,
    private var syncedIndices: List<Int>,
    private var smoothScroll: Boolean,
) {
    operator fun component1(): Int = selectedTabIndex
    operator fun component2(): (Int) -> Unit = {
        require(it <= syncedIndices.size - 1) {
            "The selected tab's index is out of the bounds of the syncedIndices list provided. " +
                    "Either add an index to syncedIndices that corresponds to an item to your lazy list, " +
                    "or remove your excessive tab"
        }

        selectedTabIndex = it

        coroutineScope.launch {
            if (smoothScroll) {
                lazyListState.animateScrollToItem(syncedIndices[selectedTabIndex])
            } else {
                lazyListState.scrollToItem(syncedIndices[selectedTabIndex])
            }
        }
    }

    operator fun component3(): LazyListState = lazyListState
}