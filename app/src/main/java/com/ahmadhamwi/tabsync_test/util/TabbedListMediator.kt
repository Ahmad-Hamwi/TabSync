package com.ahmadhamwi.tabsync_test.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class TabbedListMediator(
    private val mRecyclerView: RecyclerView,
    private val mTabLayout: TabLayout,
    private var mIndices: List<Int>,
    private var mIsSmoothScroll: Boolean = false
) {

    private var mIsAttached = false

    private var mRecyclerState = RecyclerView.SCROLL_STATE_IDLE
    private var mTabClickFlag = false

    private var tabViewCompositeClickListener: TabViewCompositeClickListener =
        TabViewCompositeClickListener(mTabLayout)

    fun attach() {
        mRecyclerView.adapter
            ?: throw RuntimeException("Cannot attach with no Adapter provided to RecyclerView")

        if (mTabLayout.tabCount == 0)
            throw RuntimeException("Cannot attach with no tabs provided to TabLayout")

        if (mIndices.isEmpty())
            throw RuntimeException("Cannot attach with empty")

        notifyIndicesChanged()
        mIsAttached = true
    }

    fun detach() {
        clearListeners()
        mIsAttached = false
    }

    fun updateMediatorWithNewIndices(newIndices: List<Int>): TabbedListMediator {
        mIndices = newIndices
        return this
    }

    private fun clearListeners() {
        mRecyclerView.clearOnScrollListeners()
        for (i in 0 until mTabLayout.tabCount) {
            mTabLayout.getTabAt(i)!!.view.setOnClickListener(null)
        }
        for (i in tabViewCompositeClickListener.getListeners().indices) {
            tabViewCompositeClickListener.getListeners().toMutableList().removeAt(i)
        }
    }

    private fun notifyIndicesChanged() {

        tabViewCompositeClickListener.addListener { _, _ -> mTabClickFlag = true }
        tabViewCompositeClickListener.build()
        mTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                if (!mTabClickFlag) return

                val position = tab.position

                if (mIsSmoothScroll) {
                    mRecyclerView.scrollToPosition(mIndices[position])
                } else {
                    (mRecyclerView.layoutManager as LinearLayoutManager?)?.scrollToPositionWithOffset(
                        mIndices[position],
                        0
                    )
                    mTabClickFlag = false
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                mRecyclerState = newState
                if (mIsSmoothScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mTabClickFlag = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mTabClickFlag) {
                    return
                }

                val linearLayoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager?
                        ?: throw RuntimeException("No LinearLayoutManager attached to the RecyclerView.")

                var itemPosition =
                    linearLayoutManager.findFirstCompletelyVisibleItemPosition()

                if (itemPosition == -1) {
                    itemPosition =
                        linearLayoutManager.findFirstVisibleItemPosition()
                }

                if (mRecyclerState == RecyclerView.SCROLL_STATE_DRAGGING
                    || mRecyclerState == RecyclerView.SCROLL_STATE_SETTLING
                ) {
                    for (i in mIndices.indices) {
                        if (itemPosition == mIndices[i]) {
                            if (!mTabLayout.getTabAt(i)!!.isSelected) {
                                mTabLayout.getTabAt(i)!!.select()
                            }
                            if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == mIndices.size - 1) {
                                if (!mTabLayout.getTabAt(mIndices[mIndices.size - 1])!!.isSelected) {
                                    mTabLayout.getTabAt(mIndices[mIndices.size - 1])!!.select()
                                }
                                return
                            }
                        }
                    }
                }
            }
        })
    }

    fun isAttached(): Boolean {
        return mIsAttached
    }

    fun isSmoothScroll(): Boolean {
        return mIsSmoothScroll
    }

    fun setSmoothScroll(smooth: Boolean) {
        mIsSmoothScroll = smooth
    }

    fun addTabListener(
        listener: (tab: TabLayout.Tab, position: Int) -> Unit
    ) {
        tabViewCompositeClickListener.addListener(listener)
        if (mIsAttached) {
            notifyIndicesChanged()
        }
    }
}