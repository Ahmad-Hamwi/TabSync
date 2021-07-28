package com.ahmadhamwi.tabsync_test.ui.model

class Category(val name: String, vararg item: Item) {

    val listOfItems: List<Item> = item.toList()

}