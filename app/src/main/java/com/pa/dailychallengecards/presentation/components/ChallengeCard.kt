package com.pa.dailychallengecards.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.domain.model.ChallengeDifficulty
import com.pa.dailychallengecards.ui.theme.EASY

@Composable
fun ChallengeCard(
    challenge: Challenge
) {
    val challengeImagePainter = painterResource(id = challenge.image)

    Card(
        modifier = Modifier
            .fillMaxSize()
            .border(6.dp, Color(0xFFFFD789))
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.White)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = challengeImagePainter,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Column(
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .background(EASY),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                        .background(Color(0xFFFFD789)),
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.99f)
                            .background(Color(0xFFCCFFFC)),
                    ) {}

                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .background(Color(0xFFCCFFFC)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f)
                        .background(EASY),
                ) {}
            }
        }
    }
}