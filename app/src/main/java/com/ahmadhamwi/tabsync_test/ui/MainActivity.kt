package com.ahmadhamwi.tabsync_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmadhamwi.tabsync_test.R
import com.ahmadhamwi.tabsync_test.model.Category
import com.ahmadhamwi.tabsync_test.model.Item
import com.ahmadhamwi.tabsync_test.util.TabbedListMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {

    private val categories = mutableListOf(
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
            Item("Item 4"),
            Item("Item 5"),
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTabLayout()
        initRecycler()
        initMediator()
    }

    private fun initTabLayout() {
        for (category in categories) {
            tabLayout.addTab(tabLayout.newTab().setText(category.name))
        }
    }

    private fun initRecycler() {
        recyclerView.adapter = CategoriesAdapter(this, categories)
    }

    private fun initMediator() {
        TabbedListMediator(recyclerView, tabLayout, categories.indices.toList()).attach()
    }
}