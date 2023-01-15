package com.ahmadhamwi.tabsync_compose_example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadhamwi.tabsync_compose_example.model.Category
import com.ahmadhamwi.tabsync_compose_example.model.Item

@Composable
fun ItemCategory(
    category: Category,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(category.name, fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            category.listOfItems.forEach {
                ItemCard(item = it)
            }
        }
    }
}

@Composable
@Preview
private fun ItemCategoryPreview() {
    ItemCategory(
        Category(
            "Category 1",
            Item("Item 1"),
            Item("Item 2"),
        )
    )
}