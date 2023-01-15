package com.ahmadhamwi.tabsync_compose_example.model

class Category(val name: String, vararg item: Item) {

    val listOfItems: List<Item> = item.toList()

}

val dummyCategories = mutableListOf(
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
    Category(
        "Category 3",
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
        Item("Item 4"),
        Item("Item 5"),
        Item("Item 6"),
        Item("Item 7"),
        Item("Item 8"),
    ),
    Category(
        "Category 4",
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
        Item("Item 4"),
        Item("Item 5"),
        Item("Item 6")
    ),
    Category(
        "Category 5",
        Item("Item 1"),
        Item("Item 2"),
        Item("Item 3"),
        Item("Item 4"),
    ),
)