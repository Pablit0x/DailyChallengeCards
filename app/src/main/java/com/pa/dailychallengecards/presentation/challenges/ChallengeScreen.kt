package com.pa.dailychallengecards.presentation.challenges

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.presentation.components.ChallengeCard
import com.pa.dailychallengecards.presentation.components.SelectionDialog
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {


    //Daily Challenge
    val dailySelection by viewModel.dailySelection.collectAsState(
        initial = emptyList()
    )
    val currentlySelected by viewModel.getSelectedChallenge.collectAsState(
        initial = null
    )

    //Timer
    var tempInt by remember { mutableStateOf(0) }
    var dif by remember { mutableStateOf(Duration.between(LocalDateTime.now(), LocalDateTime.now())) }

    LaunchedEffect(Unit) {
        while(true) {
            delay(1.seconds)
            dif = viewModel.updateTime()
        }
    }

    //dialog
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog) {
        SelectionDialog(
            dismiss = {openDialog = false},
            selectChallenge = {
                viewModel.updateChallengeStatus(it, ChallengeStatus.Selected)  //TODO MAKE OTHER CHALLENGES ACTIVE/COMPLETED
                viewModel.startClock() },
            threeChallenges = dailySelection.take(3)
        )
    }

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
            ChallengeCard(
                challenge = currentlySelected,
                timeLeft = dif
            )
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
                onClick = { openDialog = true }
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

