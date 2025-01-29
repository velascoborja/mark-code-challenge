package com.medtronic.surgery.app.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.medtronic.surgery.app.presentation.navigation.MainNavigation
import com.medtronic.surgery.app.presentation.navigation.MainNavigationBar
import com.medtronic.surgery.app.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                Scaffold(
                    content = { innerPadding ->
                        Surface(modifier = Modifier.padding(innerPadding)) {
                            MainNavigation(navController = navController)
                        }
                    },
                    bottomBar = {
                        MainNavigationBar(navController = navController)
                    }
                )
            }
        }
    }
}