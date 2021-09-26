# RecyclerViewSwipeDecorator #

A lightweight synchronizer between Android's TabLayout and RecyclerView.

The behavior of the synchronizer is that as you scroll through the RecyclerView’s items, the corresponding TabLayout items will be selected automatically, and vice-versa; when pressing on a TabLayout item, we want the RecyclerView to scroll to the corresponding item to view.


![Telegram example](https://media.giphy.com/media/NWCPDzplCS4BpMd9Tc/giphy.gif)
![Mediator attached](https://media.giphy.com/media/T1cDzfvY3KzQn7kp5d/giphy.gif)
![Mediator attached with smooth scroll](https://giphy.com/gifs/MTS4wKN5EenEqgCPw7)

## How do I get set up? ##

Get latest version via Maven Central:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ahmad-hamwi/tabsync)

If you use Gradle add Maven Central repository in your root build.gradle at the end of repositories:

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
    implementation 'io.github.ahmad-hamwi:tabsync:1.0.0'
}
```

## Usage ##

Here is a non-comprehensive guide to TabSync:

Create an TabbedListMediator, and pass a RecyclerView, a TabLayout, and a list of indices of the
RecyclerView's items that you want to the tabs with.

```kotlin
val mediator = TabbedListMediator(
    recyclerView,
    tabLayout,
    categories.indices.toList()
)
```

Make sure that RecyclerView and the TabLayout are instantiated with their data (adapter with its
data for RecyclerView, and tabs for the TabLayout) and call the attach method. Note that the tabs
count must not be less that the passed indices.

```kotlin
method.attach()
```

In order to make the RecycerView smooth scrolls when pressing on a tab, you can pass a smooth scroll
flag to the mediator:

```kotlin
val mediator = TabbedListMediator(
    recyclerView,
    tabLayout,
    categories.indices.toList(),
    true
)
```

In order to stop syncing between the RecyclerView and the TabLayout, call the detach method:

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
