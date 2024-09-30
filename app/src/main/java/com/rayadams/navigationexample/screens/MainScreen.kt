package com.rayadams.navigationexample.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rayadams.navigationexample.controls.TopBar
import com.rayadams.navigationexample.navigation.NavRoutes
import com.rayadams.navigationexample.screens.view_models.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        TopBar("Main Screen")
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Simple navigate")
            Button(onClick = { viewModel.navigateToScreen1() }) {
                Text("To Screen 1")
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text("Navigate and clear TOP making Screen2 a new top screen.")
            Button(onClick = { viewModel.navigateToScreen2() }) {
                Text("To Screen 2")
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))

            Text("Navigate with logic in ViewModel. \nSelect destination")
            Row {
                Text(viewModel.choiceText, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {
                        expanded = true
                    })
                Icon(Icons.Default.ArrowDropDown, contentDescription = "", modifier = Modifier.clickable {
                    expanded = true
                })
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(text = {
                        Text("Screen 1")
                    }, onClick = {
                        expanded = false
                        viewModel.setChoiceTextValue("Screen 1")
                    })
                    DropdownMenuItem(text = {
                        Text("Screen 2")
                    }, onClick = {
                        expanded = false
                        viewModel.setChoiceTextValue("Screen 2")
                    })
                }
            }
            Button(onClick = { viewModel.navigateByChoice() }) {
                Text("Navigate")
            }
            HorizontalDivider()
            Spacer(modifier = Modifier.height(10.dp))
            Text("Navigate with parameter")
            TextField(value = viewModel.parameter,
                label = {
                        Text("Enter text")
                },
                onValueChange = {
                    viewModel.setParameterValue(it)
                })
            Button(onClick = { viewModel.navigateWithParameters() }) {
                Text("Navigate")
            }
        }
    }
}