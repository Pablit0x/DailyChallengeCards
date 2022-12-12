package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.dailychallengecards.presentation.components.TopBar


@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Text(text = "asd", modifier = Modifier.padding(padding))
        }
    )
}


