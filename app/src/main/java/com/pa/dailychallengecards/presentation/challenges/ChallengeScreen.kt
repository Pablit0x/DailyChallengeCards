package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.domain.model.ChallengeStatus
import com.pa.dailychallengecards.presentation.components.ChallengeCard
import com.pa.dailychallengecards.presentation.components.UnselectedChallengeCard
import com.pa.dailychallengecards.ui.theme.EASY
import com.pa.dailychallengecards.ui.theme.orange

@Composable
fun ChallengeScreen(){
    var isChallengeSelected: Boolean by remember { mutableStateOf(false) }
    var c : Int by remember { mutableStateOf(0) }

    val backgroundPainter = painterResource(id = com.pa.dailychallengecards.R.drawable.lol2)
    val arrowPainter = painterResource(id = com.pa.dailychallengecards.R.drawable.ic_baseline_keyboard_arrow_down_48)

    val mockChallenge = Challenge(
        id = 0,
        description = "mock challenge",
        title = "be kind",
        difficulty = ChallengeDifficulty.EASY,
        image = com.pa.dailychallengecards.R.drawable.asd,
        status = ChallengeStatus.Active
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

//        Image(
//            modifier = Modifier
//                .fillMaxSize(),
//            painter = backgroundPainter,
//            contentDescription = "background",
//            contentScale = ContentScale.Crop
//        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF363636))
        )

        var color = when(c){
            0 -> Color(0xFF474747)
            1 -> EASY
            else -> Color(0xFF39A6B9)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(color)
        )

//
//        Row(
//            modifier = Modifier
//                .fillMaxHeight()
//                .offset(0.dp, 180.dp),
//            verticalAlignment = Alignment.Top) {
//            Text(
//                text = "Your Current Challenge is...",
//                fontSize = 35.sp,
//                color = Color.White
//            )
//
//        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.745f)
                .fillMaxHeight(0.46f)
                .clickable {
                    isChallengeSelected = !isChallengeSelected
                    c = if (isChallengeSelected) 1 else 0
                }
        ) {
            if(isChallengeSelected){
                ChallengeCard(mockChallenge)
            } else {
                UnselectedChallengeCard()
            }
        }


        Image(
            modifier = Modifier
                .fillMaxHeight(0.96f),

            painter = arrowPainter,
            contentDescription = "",
            alignment = Alignment.BottomCenter
        )
    }
}