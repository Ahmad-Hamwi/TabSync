# TabSync #

A lightweight synchronizer between Android's Tabs and Lists! 

## Available on the View System & Jetpack Compose ##

- On Android's [View system](https://github.com/Ahmad-Hamwi/TabSync#setup-for-the-view-system), synchronization is made between TabLayout and RecyclerView
- Android's [Jetpack Compose](https://github.com/Ahmad-Hamwi/TabSync#setup-for-jetpack-compose), the synchronization is made between any LazeListState-based Composable (e.g. LazyColumn) and an index-based Composable (e.g. ScrollableTabRow).

## Behaviour expected ##

As you scroll through items, the corresponding tabs will be selected automatically, and vice-versa; selecting tabs by hand will correspond to a scroll of the list to the corresponding item.


![Mediator attached](https://media.giphy.com/media/T1cDzfvY3KzQn7kp5d/giphy.gif)
![Mediator attached with smooth scroll](https://media.giphy.com/media/MTS4wKN5EenEqgCPw7/giphy.gif)

## Articles ##
- RecyclerView and TabLayout: Here's a [Medium Article](https://ahmad-hamwi.medium.com/synchronize-recyclerview-with-tablayout-3c5da4f3b18b) demonstrating the example in this repo step-by-step.
- Jetpack compose: Here's the Jetpack Compose version [Medium Article](https://proandroiddev.com/jetpack-compose-synchronize-lazyliststate-with-scrollabletabrow-22ff1f8577dc) demonstrating the example in this repo step-by-step, and explaining how it's working.

# Setup for the View system #

Get the latest version via Maven Central:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync)

Add Maven Central repository to your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

Then add the dependency

```groovy
dependencies {
    implementation 'io.github.ahmad-hamwi:tabsync:1.0.1'
}
```

## Usage ##

Here is a non-comprehensive guide to TabSync:

Create a TabbedListMediator, and pass a RecyclerView, a TabLayout, and a list of RecyclerView's items indices that you wish to sync the tabs with.

```kotlin
val mediator = TabbedListMediator(
    recyclerView,
    tabLayout,
    categories.indices.toList()
)
```

Make sure that RecyclerView and the TabLayout are instantiated with their data (adapter with its
data for RecyclerView, and tabs for the TabLayout) and call the attach method. Note that the tabs'
count must not be less than the number of the passed indices.

```kotlin
method.attach()
```

To make the RecycerView smooth scrolls when pressing on a tab, you can pass a smooth scroll
flag to the mediator:

```kotlin
val mediator = TabbedListMediator(
    recyclerView,
    tabLayout,
    categories.indices.toList(),
    true
)
```

To stop syncing between the RecyclerView and the TabLayout, call the detach method:

```kotlin
mediator.detach()
```

In case of refreshing the mediator (like data set has been changed), call the reAttach method:

```kotlin
mediator.reAttach()
```

To get the smooth scroll flag, call the getter isSmoothScroll()

```kotlin
val isSmoothScrolling = mediator.isSmoothScroll()
```

> **_NOTE:_**  You don't have to pass the full indices of the list provided as shown in the previous example. You may want to provide a couple of indices from your recyclerview's items to sync the tabs with:
> ```kotlin
>  val mediator = TabbedListMediator(
>    recyclerView,
>    tabLayout,
>    // Syncing the first tab with the item of index 0, the second one with the item of
>    // index 2, and the third with the item of index 4
>    mutableListOf(0, 2, 4) 
>  )
> ```

# Setup for Jetpack Compose #

Get the latest version via Maven Central:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync-compose/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync-compose)

Add Maven Central repository to your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        ...
        mavenCentral()
    }
}
```

Then add the dependency

```groovy
dependencies {
    implementation 'io.github.ahmad-hamwi:tabsync-compose:1.0.0'
}
```

## Usage ##

Here is a non-comprehensive guide to TabSync Compose:

Call ```lazyListTabSync``` composable, and pass the indices of your list that you wish to sync the tabs with, and assign the result to a destructuring declaration like so:

```kotlin
@Composable
fun MyComposable(items: List<Item>) {

    val (selectedTabIndex, setSelectedTabIndex, syncedListState) = lazyListTabSync(items.indices.toList())

}
```

Make sure that whenever you call ```lazyListTabSync```, the indices and the list's data are ready (above we're passing the state as a parameter). 

Note that the tabs' count must not be less than the number of the passed indices.


- The ```selectedTabIndex``` is the state of a ```ScrollableTabRow``` that we're going to sync with.
- The ```setSelectedTabIndex``` is a function that we should call whenever the state of the ```ScrollableTabRow``` changes directly, for example by pressing the tab.


``` kotlin
    ScrollableTabRow(selectedTabIndex) {
        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { setSelectedTabIndex(index) },
            )
        }
    }
```

- The ```listState``` is a ```LazyListState``` which should be attached to a scrollable that accepts it, for example a ```LazyColumn```.

```kotlin
    LazyColumn(state = syncedListState) {
        ...
    }
```

### Combined: ###

(Full example can be found under [compose-app](https://github.com/Ahmad-Hamwi/TabSync/tree/master/compose-app))

```kotlin
@Composable
fun MyComposable(categories: List<Category>) {
    val (selectedTabIndex, setSelectedTabIndex, syncedListState) = lazyListTabSync(categories.indices.toList())

    Column {
        ScrollableTabRow(selectedTabIndex) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { setSelectedTabIndex(index) },
                    ...
                )
            }
        }

        LazyColumn(state = syncedListState) {
            ...
        }
    }
}
```

> **_NOTE:_**  You don't have to pass the full indices of the list provided as shown in the previous example. You may want to provide a couple of indices from your list's items to sync the tabs with:
> ```kotlin
>    // Syncing the first tab with the item of index 0, the second one with the item of
>    // index 2, and the third with the item of index 4
>    val (selectedTabIndex, setSelectedTabIndex, syncedListState) = lazyListTabSync(mutableListOf(0, 2, 4))
> ```

### Additional arguments ###

- By default, the list smooth scrolls when the selected tab changes, if you would like to stop the smooth scrolling, you can pass an optional ```smoothScroll``` flag:
- ```tabsCount``` can be used to check for the viability of the synchronization with the tabs, the optimal case is that the number of tabs that we're syncing with is the same as the number of indices provided. (You cannot have a number of tabs lower than the number of indices provided, use ```tabsCount``` to make sure you're not doing that).
- ```lazyListState``` can be used if you would like to provide your own state, the one coming from the destructuring declaration is going to be the same.

```kotlin
@Composable
fun MyComposable(items: List<Item>) {

    val (selectedTabIndex, setSelectedTabIndex, listState) = tabSyncMediator(
        mutableListOf(0, 2, 4), //Mandatory. The indices of lazy list items to sync the tabs with
        tabsCount = 3, //Optional. To check for viability of the synchronization with the tabs. Optimal when equals the count of syncedIndices.
        lazyListState = rememberLazyListState(), //Optional. To provide your own LazyListState. Defaults to rememberLazyListState().
        smoothScroll = true, // Optional. To make the auto scroll smooth or not when clicking tabs. Defaults to true
    )

}
```

# Contributing #
This library is made to help other developers out in their app developments, feel free to contribute by suggesting ideas and creating issues and PRs that would make this repository more helpful.

# License

Copyright (C) 2023 Ahmad Hamwi

Licensed under the Apache License, Version 2.0
