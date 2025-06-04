package com.example.firsttestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.example.firsttestapp.presentation.HomeScreen.view.HomeScreen
import com.example.firsttestapp.presentation.ui.theme.FirsttestappTheme
import com.example.firsttestapp.presentation.ui.theme.NetBarTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(android.graphics.Color.WHITE,android.graphics.Color.WHITE),
            navigationBarStyle = SystemBarStyle.auto(android.graphics.Color.WHITE, android.graphics.Color.WHITE)
        )

        setContent {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                NetBarTheme {

                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                            HomeScreen().onCreate()
                        }
                    }

                }
            }
        }
    }
}


