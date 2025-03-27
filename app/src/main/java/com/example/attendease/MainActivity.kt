package com.example.attendease

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel
import com.example.attendease.core.statistics.viewModel.StatisticsViewModel
import com.example.attendease.router.NavigationHost
import com.example.attendease.screens.LoadingScreen
import com.example.attendease.ui.theme.AttendEaseTheme

class MainActivity : ComponentActivity() {
    private val attendiesViewModel: AttendiesViewModel by viewModels {
        AttendiesViewModel.Factory(
            (application as AttendEaseApplication).attendiesRepository
        )
    }

    private val statisticsViewModel: StatisticsViewModel by viewModels {
        StatisticsViewModel.Factory(
            (application as AttendEaseApplication).statisticsRepository
        )
    }

    private val helpSupportViewModel: HelpSupportViewModel by viewModels {
        HelpSupportViewModel.Factory(
            (application as AttendEaseApplication).helpSupportRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendEaseTheme {
                var isLoading by remember { mutableStateOf(true) } // Ajoute une variable d'état pour l'écran de chargement

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (isLoading) {
                        // Affiche l'écran de chargement et passe à l'écran principal après chargement
                        LoadingScreen(onLoadingComplete = { isLoading = false })
                    } else {
                        val navController = rememberNavController()
                        val context = LocalContext.current
                        val pref = context.getSharedPreferences("local_AttendEase_data", Context.MODE_PRIVATE)

                        NavigationHost(
                            navController = navController,
                            pref = pref,
                            attendiesViewModel = attendiesViewModel,
                            statisticsViewModel = statisticsViewModel,
                            helpSupportViewModel = helpSupportViewModel
                        )
                    }
                }
            }
        }
    }
}
