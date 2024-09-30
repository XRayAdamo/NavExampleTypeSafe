package com.rayadams.navigationexample.navigation

import android.os.Parcelable
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject
import javax.inject.Singleton


enum class NavigationCommand {
    /**
     * Navigate to destination
     */
    NAVIGATE,

    /**
     * Go back
     */
    GO_BACK,

    /**
     * Navigate back to specific destination
     */
    GO_BACK_TO,

    /**
     * Navigate back to specific destination, inclusive=true
     */
    GO_BACK_TO_INCLUSIVE,

    /**
     * Navigate to destination and remove current view from nav stack
     */
    NAVIGATE_AND_CLEAR_CURRENT,

    /**
     * Navigate to destination and remove all previous destinations making current one as a top
     */
    NAVIGATE_AND_CLEAR_TOP
}

data class NavigationAction(
    val navigationCommand: NavigationCommand,
    val parcelableArguments: Map<String, Parcelable> = emptyMap(),
    val navOptions: NavOptions = NavOptions.Builder().build(),        // No NavOptions as default,
    val typeSafeDestination: Any? = null
)

@Suppress("unused")
@Singleton
class CustomNavigator @Inject constructor() {
    private val _navActions =
        MutableSharedFlow<NavigationAction>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    val navActions: SharedFlow<NavigationAction> = _navActions

    fun navigate(navAction: NavigationAction) {
        _navActions.tryEmit(navAction)
    }

    fun navigate(data: Any) {
        navigate(NavigationAction(NavigationCommand.NAVIGATE, typeSafeDestination = data))
    }

    fun navigateAndClear(destination: Any) {
        navigate(NavigationAction(NavigationCommand.NAVIGATE_AND_CLEAR_TOP, typeSafeDestination = destination))
    }

    fun goBack() {
        navigate(NavigationAction(NavigationCommand.GO_BACK))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun runNavigationCommand(action: NavigationAction, navController: NavHostController) {
        when (action.navigationCommand) {
            NavigationCommand.GO_BACK -> navController.navigateUp()
            NavigationCommand.GO_BACK_TO -> {
                navController.goBackTo(action.typeSafeDestination!!, inclusive = false)
            }

            NavigationCommand.NAVIGATE -> {
                navController.navigate(action.typeSafeDestination!!) {
                    launchSingleTop = true
                }
            }

            NavigationCommand.NAVIGATE_AND_CLEAR_CURRENT -> {
                navController.navigate(action.typeSafeDestination!!) {
                    navController.currentBackStackEntry?.destination?.route?.let {
                        popUpTo(it) { inclusive = true }
                    }
                }
            }

            NavigationCommand.NAVIGATE_AND_CLEAR_TOP -> {
                navController.navigateAndReplaceStartRoute(action.typeSafeDestination!!)
            }

            NavigationCommand.GO_BACK_TO_INCLUSIVE -> {
                navController.goBackTo(action.typeSafeDestination!!, inclusive = true)
            }
        }

        _navActions.resetReplayCache()
    }

    fun goBackTo(destination: Any) {
        navigate(NavigationAction(NavigationCommand.GO_BACK_TO, typeSafeDestination = destination))
    }

    fun goBackTo(destination: Any, inclusive: Boolean) {
        if (inclusive) {
            navigate(NavigationAction(NavigationCommand.GO_BACK_TO_INCLUSIVE, typeSafeDestination = destination))
        } else {
            navigate(NavigationAction(NavigationCommand.GO_BACK_TO, typeSafeDestination = destination))
        }
    }

    fun navigateAndClearCurrentScreen(destination: Any) {
        navigate(NavigationAction(NavigationCommand.NAVIGATE_AND_CLEAR_CURRENT, typeSafeDestination = destination))
    }
}

fun NavHostController.navigateAndReplaceStartRoute(newHomeRoute: Any) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(newHomeRoute)
    navigate(newHomeRoute)
}

fun NavHostController.goBackTo(routeName: Any, inclusive: Boolean = false) {
    popBackStack(routeName, inclusive)
}
