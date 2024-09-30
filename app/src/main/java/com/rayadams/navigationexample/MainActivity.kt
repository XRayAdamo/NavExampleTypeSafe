package com.rayadams.navigationexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavRoutes
import com.rayadams.navigationexample.screens.MainScreen
import com.rayadams.navigationexample.screens.Screen1
import com.rayadams.navigationexample.screens.Screen2
import com.rayadams.navigationexample.screens.Screen3
import com.rayadams.navigationexample.screens.ScreenWithParameter
import com.rayadams.navigationexample.ui.theme.NavigationExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navHelper: CustomNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationExampleTheme {
                val navController: NavHostController = rememberNavController()

                LaunchedEffect(key1 = true) {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            navHelper.navActions.collect { navigatorState ->
                                navigatorState.parcelableArguments.forEach { arg ->
                                    navController.currentBackStackEntry?.arguments?.putParcelable(
                                        arg.key,
                                        arg.value
                                    )
                                }
                                navHelper.runNavigationCommand(navigatorState, navController)

                            }
                        }
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = NavRoutes.MainScreen
    ) {
        composable<NavRoutes.MainScreen> {
            MainScreen()
        }
        composable<NavRoutes.Screen1> {
            Screen1()
        }
        composable<NavRoutes.Screen2> {
            Screen2()
        }
        composable<NavRoutes.Screen3> {
            Screen3()
        }
        composable<NavRoutes.ScreenWithParameter> { backStack ->
            val data: NavRoutes.ScreenWithParameter = backStack.toRoute()
            ScreenWithParameter(data.parameter)
        }
    }
}
