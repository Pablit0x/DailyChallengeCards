package com.pa.dailychallengecards.presentation.challenges


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.domain.model.ChallengeStatus

@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {
    val dailySelection by viewModel.dailySelection.collectAsState(
        initial = emptyList()
    )
    var tempInt by remember { mutableStateOf(0) }

    //Background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
    }

    //Challenge presentation
    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var result = ""
        dailySelection.forEach {
            result += "ID = ${it.id}, TITLE = ${it.title}, DESCRIPTION = ${it.description} \n\n"
        }
        Text(text = result)
        }


    //Challenge Admin options
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
        ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.05f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.1f),
                onClick = { tempInt = addChallenge(tempInt, viewModel) }
            ) {
                Text(text = "+")
            }

            Button(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.1f),
                onClick = { viewModel.deleteAllChallenges() }
            ) {
                Text(text = "-")
            }
        }
    }
    }


fun addChallenge(id : Int, viewModel: ChallengesViewModel) : Int{
    val newId = id + 1

    val newChallenge = Challenge(
        id = id,
        title = "Test: $id",
        description = "TestDescription",
        image = R.drawable.the_background_image,
        difficulty = ChallengeDifficulty.EASY,
        status = ChallengeStatus.Active
    )
    viewModel.addChallenge(newChallenge)

    return newId
}