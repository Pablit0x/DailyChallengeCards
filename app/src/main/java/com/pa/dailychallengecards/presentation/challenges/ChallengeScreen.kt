package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.presentation.components.ChallengeCards

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel = hiltViewModel()
) {
//    val dailySelection by viewModel.dailySelection.collectAsState(
//        initial = emptyList()
//    )

    val challenge1 = Challenge(
        title = "Get Moving",
        description = "Take a walk, go for a run, or try a new workout to get your body moving.",
        image = R.drawable.walk,
        difficulty = ChallengeDifficulty.EASY,
        status = ChallengeStatus.Active
    )

    val challenge2 = Challenge(
        title = "Connect with Nature",
        description = "Go outside and appreciate the beauty of the natural world around you.",
        image = R.drawable.chill,
        difficulty = ChallengeDifficulty.EASY,
        status = ChallengeStatus.Active
    )

    val challenge3 = Challenge(
        title = "Learn Something New",
        description = "Pick up a new skill or hobby and challenge yourself to learn something new.",
        image = R.drawable.skill,
        difficulty = ChallengeDifficulty.EASY,
        status = ChallengeStatus.Active
    )
    val dailySelection = mutableListOf(challenge1, challenge2, challenge3)
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    val rotationState by animateFloatAsState(
        targetValue = if (bottomSheetState.isExpanded) 180f else 0f
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {

            Icon(
                Icons.Default.KeyboardArrowUp,
                "Arrow Icon",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .rotate(rotationState)
                    .align(CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                content = {
                    items(50) { i ->
                        var image : Int
                        image = if (i % 2 == 0) {
                            R.drawable.skill
                        } else if (i % 3 == 0){
                            R.drawable.chill
                        } else {
                            R.drawable.walk
                        }
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(5.dp)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(painter = painterResource(id = image), contentDescription = "")
                        }
                    }
                })
        },
        sheetBackgroundColor = Color.DarkGray,
        sheetPeekHeight = 32.dp,
    ) { padding ->
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ChallengeCards(dailySelection)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    dailySelection.remove(challenge2)
                    dailySelection.remove(challenge3)
                    // Handle click events here
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.DarkGray
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text("Select", color = Color.White, fontSize = 32.sp)
            }

        }
    }
}