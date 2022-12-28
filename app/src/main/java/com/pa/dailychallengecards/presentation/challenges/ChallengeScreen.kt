package com.pa.dailychallengecards.presentation.challenges


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.presentation.components.ChallengeCard
import com.pa.dailychallengecards.presentation.components.SelectionDialog

@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {
    val dailySelection by viewModel.dailySelection.collectAsState(
        initial = emptyList()
    )
    val currentlySelected by viewModel.getSelectedChallenge.collectAsState(
        initial = null
    )

    var tempInt by remember { mutableStateOf(0) }

    //Background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
    ) {

    }

    //Challenge presentation
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, 0.dp, 8.dp, 0.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row( modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.8f)
        ) {
            ChallengeCard(challenge = currentlySelected)

        }
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
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = { tempInt = addChallenge(tempInt, viewModel) }
            ) {
                Text(text = "+")
            }

            Button(
                modifier = Modifier
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                onClick = {
                    viewModel.updateChallengeStatus(dailySelection.random().id!!,ChallengeStatus.Selected)
                    //TODO MAKE OTHER CHALLENGES ACTIVE/COMPLETED
                }
            ) {
                Text(text = "Roll [${dailySelection.size}]")
            }

            Button(
                modifier = Modifier
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
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
        description = "be kind to someone i don't know",
        image = R.drawable.hmm,
        difficulty = ChallengeDifficulty.EASY,
        status = ChallengeStatus.Active
    )
    viewModel.addChallenge(newChallenge)

    return newId
}