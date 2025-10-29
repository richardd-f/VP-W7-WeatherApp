package com.felix.labw7_weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felix.labw7_weatherapp.ui.theme.LabW7_WeatherAppTheme
import com.felix.labw7_weatherapp.ui.screens.homepage.Homepage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabW7_WeatherAppTheme {
                Homepage()
            }
        }
    }
}