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
import com.rayadams.navigationexample.screens.view_models.Screen2ViewModel

@Composable
fun Screen2(viewModel: Screen2ViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar("Screen 2")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            Text("Pressing back from this screen will close application")
            Spacer(modifier = Modifier.height(10.dp))
            Text("Pressing this button will make Main Screen top screen again")
            Button(onClick = { viewModel.navigateToMainScreen() }) {
                Text("Make Main Screen top again")
            }
        }
    }
}