package com.pa.dailychallengecards.presentation.challenges

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pa.dailychallengecards.presentation.components.ChallengeCard
import com.pa.dailychallengecards.presentation.components.UnselectedChallengeCard

@Composable
fun ChallengeScreen(){
    var isChallengeSelected: Boolean by remember { mutableStateOf(false) }
    val painter = painterResource(id = com.pa.dailychallengecards.R.drawable.the_background_image)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painter,
            contentDescription = "background",
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .border(5.dp, Color.White)
                .shadow(5.dp)
                .clickable {
                    isChallengeSelected = !isChallengeSelected
                }
        ) {
            if(isChallengeSelected){
                ChallengeCard()
            } else {
                UnselectedChallengeCard()
            }
        }
    }
}