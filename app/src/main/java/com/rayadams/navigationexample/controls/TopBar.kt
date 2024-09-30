package com.rayadams.navigationexample.controls

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "", onButtonClicked: (() -> Unit)? = null
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    TopAppBar(scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = "TopAppBar" },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 48.dp)
            )
        },
        navigationIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onButtonClicked?.invoke() },
                    modifier = Modifier.semantics { contentDescription = "TopAppBarButton" }) {
                    if (onButtonClicked!=null) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "TopAppBarButtonIcon"
                        )
                    } else {
                        Box(modifier = Modifier.size(48.dp))
                    }
                }
            }
        })
}