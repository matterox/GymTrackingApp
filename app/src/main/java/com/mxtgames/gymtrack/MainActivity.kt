package com.mxtgames.gymtrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mxtgames.gymtrack.authentication.ui.AuthRoute
import com.mxtgames.gymtrack.authentication.ui.AuthScreen
import com.mxtgames.gymtrack.authentication.ui.AuthenticationViewModel
import com.mxtgames.gymtrack.ui.theme.GymTrackTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import java.io.Closeable
import kotlin.time.TimeSource

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymTrackTheme {
                NavigationHolder()
            }
        }
    }
}

@Composable
fun NavigationHolder() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Auth.route) {
        composable(route = Destination.Auth.route) {
            AuthRoute()
        }
    }

}