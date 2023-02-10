package com.example.wings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun WingsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()

    // fetch current Destination
    val currentDestination = currentBackStack?.destination

    Scaffold(
        topBar = {
        }
    ) { innerPadding ->
        WingsNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}
