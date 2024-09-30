package com.rayadams.navigationexample.screens.view_models

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.ref.WeakReference
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val customNavigator: CustomNavigator,
    @ApplicationContext context: Context
) : ViewModel() {

    private val context = WeakReference(context)

    var choiceText by mutableStateOf("Screen 1")
        private set

    var parameter by mutableStateOf("")
        private set

    fun setChoiceTextValue(newValue: String) {
        choiceText = newValue
    }

    fun setParameterValue(newValue: String) {
        parameter = newValue
    }

    fun navigateToScreen1() {
        customNavigator.navigate(NavRoutes.Screen1)
    }

    fun navigateToScreen2() {
        customNavigator.navigateAndClear(NavRoutes.Screen2)
    }

    fun navigateByChoice() {
        if (choiceText == "Screen 1") {
            customNavigator.navigate(NavRoutes.Screen1)
        } else {
            customNavigator.navigateAndClear(NavRoutes.Screen2)
        }
    }

    fun navigateWithParameters() {
        if (parameter.isBlank()) {
            Toast.makeText(context.get()!!, "Please enter text", LENGTH_SHORT).show()
        } else {
            customNavigator.navigate(NavRoutes.ScreenWithParameter(parameter))
        }
    }
}