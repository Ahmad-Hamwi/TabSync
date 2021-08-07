package com.ahmadhamwi.tabsync_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.ahmadhamwi.tabsync_test.R
import com.ahmadhamwi.tabsync_test.model.Category
import com.ahmadhamwi.tabsync_test.model.Item
import com.ahmadhamwi.tabsync.TabbedListMediator
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var recyclerView: RecyclerView

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

        initViews()
        initTabLayout()
        initRecycler()
        initMediator()
    }

    private fun initViews() {
        tabLayout = findViewById(R.id.tabLayout)
        recyclerView = findViewById(R.id.recyclerView)
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
        TabbedListMediator(
            recyclerView,
            tabLayout,
            categories.indices.toList()
        ).attach()
    }
}