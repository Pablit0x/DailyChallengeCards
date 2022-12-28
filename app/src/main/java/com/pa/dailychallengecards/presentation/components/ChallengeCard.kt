package com.pa.dailychallengecards.presentation.components

import androidx.compose.foundation.Image
import com.pa.dailychallengecards.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.ui.theme.COLOUREASY
import com.pa.dailychallengecards.ui.theme.COLOURHARD

@Composable
fun ChallengeCard(
    challenge: Challenge?
) {

    var fonts = FontFamily(
        Font(R.font.bebasneue_regular)
    )
    var output = ""
    var color : Color
    var colorText : Color
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
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = "Today i will try to " +output,
                fontSize = 28.sp,
                color = colorText,
                fontFamily = FontFamily.Monospace
            )

            Image(
                painter = painter1,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}