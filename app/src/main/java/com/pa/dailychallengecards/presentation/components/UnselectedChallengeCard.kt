package com.pa.dailychallengecards.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.ui.theme.Jose
import com.pa.dailychallengecards.ui.theme.question_mark_color
import com.pa.dailychallengecards.ui.theme.text_color
import com.pa.dailychallengecards.ui.theme.unselected_card_background_color

@Composable
fun UnselectedChallengeCard(){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .border(2.dp,Color.White)
    ) {
        Column(
            modifier = Modifier
                .background(unselected_card_background_color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row() {
                Text(
                    text = "?",
                    fontSize = 350.sp,
                    color = question_mark_color,
                    fontFamily = Jose
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(8.dp, 24.dp, 8.dp, 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            Row() {
                Text(
                    text = "UNSELECTED",
                    fontSize = 40.sp,
                    color = text_color,
                    fontFamily = FontFamily.Default
                )
            }

            Row() {
                Text(
                    text = "-Tap to Select-",
                    fontSize = 18.sp,
                    color = text_color
                )
            }
        }
    }
}