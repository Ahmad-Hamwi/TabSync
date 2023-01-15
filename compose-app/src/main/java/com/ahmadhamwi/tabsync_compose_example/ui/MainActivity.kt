package com.ahmadhamwi.tabsync_compose_example.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadhamwi.tabsync_compose_example.model.dummyCategories
import com.ahmadhamwi.tabsync_compose_example.ui.components.TabSyncComposeScreen
import com.ahmadhamwi.tabsync_compose_example.ui.theme.TabsyncTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TabSyncComposeApp() }
    }
}

@Preview
@Composable
fun TabSyncComposeApp() {
    TabsyncTheme {
        TabSyncComposeScreen(dummyCategories)
    }
}
