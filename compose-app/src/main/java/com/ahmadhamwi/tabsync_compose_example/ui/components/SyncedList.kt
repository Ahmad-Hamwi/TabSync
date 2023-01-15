package com.ahmadhamwi.tabsync_compose_example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadhamwi.tabsync_compose_example.model.Category
import com.ahmadhamwi.tabsync_compose_example.model.Item

@Composable
fun MyLazyList(
    categories: List<Category>,
    listState: LazyListState = rememberLazyListState(),
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { _, category ->
            ItemCategory(category)
        }
    }
}

@Composable
@Preview
fun SyncedLazyListPreview() {
    MyLazyList(
        listOf(
            Category(
                "Category 1",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
                Item("Item 5"),
                Item("Item 6")
            ),
            Category(
                "Category 2",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
            ),
        ),
        rememberLazyListState()
    )
}