package com.pa.dailychallengecards.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pa.dailychallengecards.domain.model.Challenge

@Composable
fun SelectionDialog(
    dismiss : () -> Unit,
    selectChallenge : (Int) -> Unit,
    threeChallenges : List<Challenge>,


){
    var highlightedID: Int? by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = {
            dismiss()
        },

        backgroundColor = Color.LightGray,
        title = {},
        text = {
            LazyColumn(){
                items(items = threeChallenges, itemContent = { i ->
                    var backgroundColor = Color.Transparent
                    if (highlightedID == i.id){
                        backgroundColor = Color.White
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp, 16.dp, 16.dp, 8.dp)
                            .background(backgroundColor)
                            .selectable(
                                selected = false,
                                onClick = {
                                    if (i.id != highlightedID) {
                                        highlightedID = i.id
                                    } else {
                                        highlightedID = null
                                    }
                                }
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,

                        ) {

                        Text(
                            text = i.title,
                            fontSize = 24.sp)
                    }
                })
            }
        },

        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp, 2.dp, 2.dp, 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {

                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .alpha(if (highlightedID != null) 1f else 0.4f),
                    onClick = {
                        if (highlightedID != null){
                            selectChallenge(highlightedID!!)
                            dismiss()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text(text = "Select", fontSize = 16.sp)
                }
            }
        }
    )

}

