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

        initTabLayout()
        initRecycler()
    }

    private fun initTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Category 1"))
        tabLayout.addTab(tabLayout.newTab().setText("Category 2"))
        tabLayout.addTab(tabLayout.newTab().setText("Category 3"))
        tabLayout.addTab(tabLayout.newTab().setText("Category 4"))
        tabLayout.addTab(tabLayout.newTab().setText("Category 5"))
    }

    private fun initRecycler() {

        val listOfCategories = mutableListOf(
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

        recyclerView.adapter = CategoriesAdapter(this, listOfCategories)
    }
}