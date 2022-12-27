package com.pa.dailychallengecards

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pa.dailychallengecards.presentation.challenges.ChallengeScreen
import com.pa.dailychallengecards.presentation.challenges.ChallengesScreen
import com.pa.dailychallengecards.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustWindow()
        setContent {
            AppTheme {
                ChallengeScreen()
            }
        }
    }

    private fun adjustWindow() {
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.setDecorFitsSystemWindows(false)
    }
}