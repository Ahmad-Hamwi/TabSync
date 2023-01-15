package com.ahmadhamwi.tabsync_compose_example.ui.components

import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadhamwi.tabsync_compose_example.model.Category

@Composable
fun MyTabBar(
    categories: List<Category>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: Category) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp
    ) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClicked(index, category) },
                text = { Text(category.name.uppercase()) }
            )
        }
    }
}

@Composable
@Preview
fun SyncedTabBarPreview() {
    var selectedIndex by remember { mutableStateOf(0) }

    MyTabBar(
        categories = mutableListOf(
            Category("Category 1"),
            Category("Category 2"),
            Category("Category 3"),
            Category("Category 4"),
            Category("Category 5"),
        ),
        selectedTabIndex = selectedIndex
    ) { index, _ ->
        selectedIndex = index
    }
}