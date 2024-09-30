package com.rayadams.navigationexample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayadams.navigationexample.controls.TopBar
import com.rayadams.navigationexample.screens.view_models.Screen1ViewModel

@Composable
fun Screen1(viewModel: Screen1ViewModel = hiltViewModel()) {

    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar("Screen 1") {
            viewModel.goBack()
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Button(onClick = { viewModel.navigateToScreen3() }) {
                Text("To Screen 3")
            }
        }
    }
}