package com.pa.dailychallengecards.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.ui.theme.question_mark_color
import com.pa.dailychallengecards.ui.theme.text_color
import com.pa.dailychallengecards.ui.theme.unselected_card_background_color

@Composable
fun UnselectedChallengeCard(){

    Column(
        modifier = Modifier
            .background(unselected_card_background_color)
            .padding(16.dp, 16.dp, 16.dp, 0.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row() {
            Text(
                text = "?",
                fontSize = 200.sp,
                color = question_mark_color
            )
        }
        Row() {
            Text(
                text = "UNSELECTED",
                fontSize = 40.sp,
                color = text_color
            )
        }
        Row() {
            Text(
                text = "Tap to Select",
                fontSize = 18.sp,
                color = text_color
            )
        }
    }
}