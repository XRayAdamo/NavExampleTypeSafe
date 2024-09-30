package com.rayadams.navigationexample.screens.view_models

import androidx.lifecycle.ViewModel
import com.rayadams.navigationexample.navigation.CustomNavigator
import com.rayadams.navigationexample.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Screen1ViewModel @Inject constructor(private val customNavigator: CustomNavigator) :ViewModel() {
    fun goBack(){
        customNavigator.goBack()
    }

    fun navigateToScreen3() {
        customNavigator.navigate(NavRoutes.Screen3)
    }
}