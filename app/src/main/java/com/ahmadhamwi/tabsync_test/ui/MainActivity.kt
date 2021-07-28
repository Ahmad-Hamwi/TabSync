package com.ahmadhamwi.tabsync_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmadhamwi.tabsync_test.R
import com.ahmadhamwi.tabsync_test.ui.model.Category
import com.ahmadhamwi.tabsync_test.ui.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.item_category.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {

        val listOfCategories = mutableListOf(
            Category(
                "Category 1",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3")
            ),
            Category(
                "Category 2",
                Item("Item 1"),
                Item("Item 2"),
            ),
            Category(
                "Category 3",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3"),
                Item("Item 4"),
                Item("Item 5"),
                Item("Item 6")
            ),
            Category(
                "Category 4",
                Item("Item 1"),
                Item("Item 2"),
                Item("Item 3")
            ),
            Category(
                "Category 5",
                Item("Item 1"),
                Item("Item 2"),
            ),
        )

        recyclerView.adapter = CategoriesAdapter(this, listOfCategories)
    }
}