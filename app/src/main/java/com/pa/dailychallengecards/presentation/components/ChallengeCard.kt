package com.pa.dailychallengecards.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.pa.dailychallengecards.domain.model.Challenge
import com.pa.dailychallengecards.ui.theme.Purple200
import com.pa.dailychallengecards.ui.theme.Shapes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Float.max
import java.lang.Float.min
import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

@Composable
fun ChallengeCard(
    dailySelection: List<Challenge>
) {
    var order by remember { mutableStateOf(listOf(0, 1, 2)) }

    Box(
        Modifier
            .background(Color.White)
            .padding(vertical = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        dailySelection.forEachIndexed { idx, challenge ->
            SwipeableCard(
                challenge = challenge,
                order = order[idx],
                totalCount = dailySelection.size,
                backgroundColor = Color.LightGray,
                onSwipe = {
                    val newOrder = order.toMutableList()
                    Collections.rotate(newOrder, 1)
                    order = newOrder.toList()
                }
            )
        }
    }
}

@Composable
fun SwipeableCard(
    challenge: Challenge,
    order: Int,
    totalCount: Int,
    backgroundColor: Color = Color.White,
    onSwipe: () -> Unit,
) {

    val animatedScale by animateFloatAsState(
        targetValue = 1f - order * 0.05f,
    )
    val animatedYOffset by animateDpAsState(
        targetValue = ((order + 1) * -12).dp,
    )

    Box(
        modifier = Modifier
            .zIndex((totalCount - order).toFloat())
            .offset { IntOffset(x = 0, y = 2 * animatedYOffset.roundToPx()) }
            .scale(animatedScale)
            .swipeToBack { onSwipe() }
            .border(2.dp, Color.Black, shape = Shapes.large)
    ) {
        SampleCard(challenge = challenge, backgroundColor = backgroundColor)
    }
}

@Composable
fun SampleCard(challenge: Challenge, backgroundColor: Color = Color.White) {
    Card(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(.8f),
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            Modifier.padding(vertical = 24.dp, horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = challenge.title)
            Spacer(Modifier.height(8.dp))
            Image(painter = painterResource(id = challenge.image), contentDescription = "")
            Spacer(Modifier.height(8.dp))
            Text(text = challenge.description)
        }
    }
}

fun Modifier.swipeToBack(
    onSwipe: () -> Unit
): Modifier = composed {
    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    var leftSide by remember { mutableStateOf(true) }

    pointerInput(Unit) {
        val decay = splineBasedDecay<Float>(this)

        coroutineScope {
            while (true) {
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                offsetY.stop()

                val velocityTracker = VelocityTracker()

                awaitPointerEventScope {
                    verticalDrag(pointerId) { change ->
                        val verticalDragOffset = offsetY.value + change.positionChange().y
                        val horizontalPosition = change.previousPosition.x

                        leftSide = horizontalPosition <= size.width / 2
                        val offsetXRatioFromMiddle = if (leftSide) {
                            horizontalPosition / (size.width / 2)
                        } else {
                            (size.width - horizontalPosition) / (size.width / 2)
                        }
                        val rotationalOffset = max(1f, (1f - offsetXRatioFromMiddle) * 4f)

                        launch {
                            offsetY.snapTo(verticalDragOffset)
                            rotation.snapTo(if (leftSide) rotationalOffset else -rotationalOffset)
                        }

                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                        change.consumePositionChange()
                    }
                }

                val velocity = velocityTracker.calculateVelocity().y
                val targetOffsetY = decay.calculateTargetValue(offsetY.value, velocity)

                if (targetOffsetY.absoluteValue <= size.height) {
                    // Not enough velocity; Reset.
                    launch { offsetY.animateTo(targetValue = 0f, initialVelocity = velocity) }
                    launch { rotation.animateTo(targetValue = 0f, initialVelocity = velocity) }
                } else {
                    // Enough velocity to fling the card to the back
                    val boomerangDuration = 600
                    val maxDistanceToFling = (size.height * 4).toFloat()
                    val maxRotations = 3
                    val easeInOutEasing = CubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f)

                    val distanceToFling = min(
                        targetOffsetY.absoluteValue + (size.height / 2), maxDistanceToFling
                    )
                    val rotationToFling = min(
                        360f * (targetOffsetY.absoluteValue / size.height).roundToInt(),
                        360f * maxRotations
                    )
                    val rotationOvershoot = rotationToFling + 12f

                    launch {
                        rotation.animateTo(targetValue = if (leftSide) rotationToFling else -rotationToFling,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                0f at 0 with easeInOutEasing
                                (if (leftSide) rotationOvershoot else -rotationOvershoot) at boomerangDuration - 50 with LinearOutSlowInEasing
                                (if (leftSide) rotationToFling else -rotationToFling) at boomerangDuration
                            })
                        rotation.snapTo(0f)
                    }
                    launch {
                        offsetY.animateTo(targetValue = 0f,
                            initialVelocity = velocity,
                            animationSpec = keyframes {
                                durationMillis = boomerangDuration
                                -distanceToFling at (boomerangDuration / 2) with easeInOutEasing
                                40f at boomerangDuration - 70
                            })
                    }
                    delay(100)
                    onSwipe()
                }
            }
        }
    }
        .offset { IntOffset(0, offsetY.value.roundToInt()) }
        .graphicsLayer {
            transformOrigin = TransformOrigin.Center
            rotationZ = rotation.value
        }
}