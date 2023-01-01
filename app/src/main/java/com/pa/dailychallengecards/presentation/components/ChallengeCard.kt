package com.pa.dailychallengecards.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.R
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.ui.theme.COLOUREASY
import com.pa.dailychallengecards.ui.theme.COLOURHARD
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun ChallengeCard(
    challenge: Challenge?,
    timeLeft : Duration
) {

    var time : String = ""+ timeLeft.toHours() +":"+ timeLeft.toMinutesPart() +":"+ timeLeft.toSecondsPart()

    var fonts = FontFamily(
        Font(R.font.bebasneue_regular)
    )
    var output = ""
    var color : Color
    var colorText : Color
    val f :DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    if(!challenge?.description.isNullOrEmpty()){
        output = challenge!!.id.toString()
        when(challenge!!.difficulty){
            ChallengeDifficulty.EASY -> {
                color = COLOUREASY
                colorText = Color.Black
            }
            ChallengeDifficulty.HARD -> {
                color = COLOURHARD
                colorText = Color.White
            }
            else -> {
                color = COLOURHARD
                colorText = Color.White
            }
        }
    } else {
        output = "..."
        color = Color.White
        colorText = Color.Black
    }

    val painter = painterResource(
        id = R.drawable.asd
    )
    val painter1 = painterResource(
        id = R.drawable.ic_outline_sports_volleyball_24
    )


    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = color,
        elevation = 8.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            //Challenge Text
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Today i will try to " + output,
                    fontSize = 28.sp,
                    color = colorText,
                    fontFamily = FontFamily.Monospace
                )
            }

            //Timer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (!challenge?.description.isNullOrEmpty()){
                    Text(
                        text = time,
                        fontSize = 68.sp,
                        color = Color(0x2F535353)
                    )
                }
            }
        }
    }
}