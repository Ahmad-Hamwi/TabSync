package com.ahmadhamwi.tabsync_compose

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*

@Composable
fun lazyListTabSync(
    syncedIndices: List<Int>,
    lazyListState: LazyListState = rememberLazyListState(),
    tabsCount: Int? = null,
    smoothScroll: Boolean = true
): TabSyncState {
    require(syncedIndices.isNotEmpty()) {
        "You can't use the mediator without providing at least one index in the syncedIndices array"
    }

    if (tabsCount != null) {
        require(tabsCount <= syncedIndices.size) {
            "The tabs count is out of the bounds of the syncedIndices list provided. " +
                    "Either add an index to syncedIndices that corresponds to an item to your lazy list, " +
                    "or remove your excessive tab"
        }
    }

    var selectedTabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }.collect {
            var itemPosition = lazyListState.findFirstFullyVisibleItemIndex()

            if (itemPosition == -1) {
                itemPosition = lazyListState.firstVisibleItemIndex
            }

            if (itemPosition == -1) {
                return@collect
            }

            if (lazyListState.findLastFullyVisibleItemIndex() == syncedIndices.last()) {
                itemPosition = syncedIndices.last()
            }

            if (syncedIndices.contains(itemPosition) && itemPosition != syncedIndices[selectedTabIndex]) {
                selectedTabIndex = syncedIndices.indexOf(itemPosition)
            }
        }
    }

    return TabSyncState(
        selectedTabIndex,
        lazyListState,
        rememberCoroutineScope(),
        syncedIndices,
        smoothScroll
    )
}