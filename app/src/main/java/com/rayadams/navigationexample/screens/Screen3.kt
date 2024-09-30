package com.rayadams.navigationexample.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayadams.navigationexample.controls.TopBar
import com.rayadams.navigationexample.screens.view_models.Screen3ViewModel

@Composable
fun Screen3(viewModel:Screen3ViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar("Screen 3")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Button(onClick = { viewModel.goBack() }) {
                Text("Go Back")
            }
            Button(onClick = { viewModel.goBackToMainScreen() }) {
                Text("Go Back To Main Screen")
            }
        }
    }
}