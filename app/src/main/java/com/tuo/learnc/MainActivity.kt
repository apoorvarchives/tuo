package com.tuo.learnc

import androidx.compose.ui.tooling.preview.Preview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContentgit checkout -b your-branch-name

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.tuo.learnc.ui.theme.LearnCPlayerTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LearnCPlayerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LearnCVideoScreen()
                }
            }
        }
    }
}

@Composable
fun LearnCVideoScreen() {
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val activity = context as? Activity
    val prefs = context.getSharedPreferences("TUO_PREFS", Context.MODE_PRIVATE)
    val lastSavedTime = prefs.getFloat("C_VIDEO_TIME", 0f)

    val progress = remember { mutableStateOf(0f) }
    var lastSavedSecond by remember { mutableStateOf(-1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Header buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { activity?.finish() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            IconButton(onClick = {
                // TODO: Handle fullscreen toggle here
            }) {
                Icon(imageVector = Icons.Filled.Fullscreen, contentDescription = "Fullscreen")
            }
        }

        // Title
        Text(
            text = "Learn C in 6hrs",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // YouTube Player
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            AndroidView(factory = { ctx ->
                YouTubePlayerView(ctx).apply {
                    lifecycle.addObserver(this)

                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(player: YouTubePlayer) {
                            player.loadVideo("irqbmMNs2Bo", lastSavedTime)

                            player.addListener(object : AbstractYouTubePlayerListener() {
                                override fun onCurrentSecond(player: YouTubePlayer, second: Float) {
                                    val secInt = second.toInt()
                                    if (secInt % 10 == 0 && secInt != lastSavedSecond.toInt()) {
                                        lastSavedSecond = second
                                        prefs.edit().putFloat("C_VIDEO_TIME", second).apply()
                                    }
                                    progress.value = second / 360f // Assuming ~6 min for demo; adjust as needed
                                }
                            })
                        }
                    })
                }
            })
        }

        // Progress bar
        Spacer(modifier = Modifier.height(16.dp))
        ProgressTracker(progress.value)

        // Spacer for additional content
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ProgressTracker(progress: Float) {
    Slider(
        value = progress,
        onValueChange = { /* Optional: Handle seek later */ },
        valueRange = 0f..1f,
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = Color.Blue,
            activeTrackColor = Color.Blue,
            inactiveTrackColor = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LearnCPreview() {
    LearnCPlayerTheme {
        LearnCVideoScreen()
    }
}
