package com.rayadams.navigationexample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayadams.navigationexample.controls.TopBar
import com.rayadams.navigationexample.screens.view_models.ScreenWithParameterViewModel

@Composable
fun ScreenWithParameter(data: String) {
    val viewModel =
        hiltViewModel<ScreenWithParameterViewModel, ScreenWithParameterViewModel.ScreenWithParameterViewModelFactory>() { factory ->
            factory.create(data)
        }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar("Screen with parameter") {
            viewModel.goBack()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text("Passed parameters is:")
            Text(viewModel.passedParameter)
        }
    }
}