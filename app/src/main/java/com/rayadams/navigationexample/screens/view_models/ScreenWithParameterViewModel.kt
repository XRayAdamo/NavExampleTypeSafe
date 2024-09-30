package com.rayadams.navigationexample.screens.view_models

import androidx.lifecycle.ViewModel
import com.rayadams.navigationexample.navigation.CustomNavigator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel(assistedFactory = ScreenWithParameterViewModel.ScreenWithParameterViewModelFactory::class)
class ScreenWithParameterViewModel @AssistedInject constructor(
    @Assisted val passedParameter: String,
    private val customNavigator: CustomNavigator
) : ViewModel() {

    fun goBack() {
        customNavigator.goBack()
    }

    @AssistedFactory
    interface ScreenWithParameterViewModelFactory {
        fun create(passedParameter: String): ScreenWithParameterViewModel
    }
}
