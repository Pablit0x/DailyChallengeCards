package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {
    val dailySelection by viewModel.dailySelection.collectAsState(
        initial = emptyList()
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var result = ""
        dailySelection.forEach {
            result += "ID = ${it.id}, TITLE = ${it.title}, DESCRIPTION = ${it.description} \n\n"
        }
        Text(text =result)
    }

}