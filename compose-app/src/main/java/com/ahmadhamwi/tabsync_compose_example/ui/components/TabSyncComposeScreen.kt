package com.ahmadhamwi.tabsync_compose_example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ahmadhamwi.tabsync_compose.lazyListTabSync
import com.ahmadhamwi.tabsync_compose_example.model.Category

@Composable
fun TabSyncComposeScreen(categories: List<Category>) {
    val (selectedTabIndex, setSelectedTabIndex, listState) = lazyListTabSync(categories.indices.toList())

//    val (selectedTabIndex, setSelectedTabIndex, listState) = tabSyncMediator(
//        mutableListOf(0, 2, 4), //Mandatory. The indices of lazy list items to sync the tabs with
//        tabsCount = 3, //Optional. To check for viability of the synchronization with the tabs. Optimal when equals the count of syncedIndices.
//        lazyListState = rememberLazyListState(), //Optional. To provide your own LazyListState. Defaults to rememberLazyListState().
//        smoothScroll = true, // Optional. To make the auto scroll smooth or not when clicking tabs. Defaults to true
//    )

    Column {
        MyTabBar(
            categories = categories,
            selectedTabIndex = selectedTabIndex,
            onTabClicked = { index, _ -> setSelectedTabIndex(index) }
        )

        MyLazyList(categories, listState)
    }
}



