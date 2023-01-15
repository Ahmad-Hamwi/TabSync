package com.ahmadhamwi.tabsync_compose_example.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadhamwi.tabsync_compose_example.model.Item

@Composable
fun ItemCard(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 64.dp),
        elevation = 8.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(item.content, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
private fun ItemCardPrev() {
    ItemCard(Item("Item 1"))
}