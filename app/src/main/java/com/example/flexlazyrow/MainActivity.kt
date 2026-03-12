package com.example.flexlazyrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.flexlazyrow.ui.theme.FlexLazyRowTheme
import io.github.karthikeyan8296.flexlazyrow.FlexLazyRow
import io.github.karthikeyan8296.flexlazyrow.FlexImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlexLazyRowTheme {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FlexLazyRow(
                        images = listOf(
                            FlexImage.UrlImage("https://picsum.photos/400/400"),
                            FlexImage.UrlImage("https://picsum.photos/401/401"),
                            FlexImage.UrlImage("https://picsum.photos/402/402"),
                            FlexImage.UrlImage("https://picsum.photos/400/400"),
                            FlexImage.UrlImage("https://picsum.photos/401/401"),
                            FlexImage.UrlImage("https://picsum.photos/402/402"),
                            FlexImage.UrlImage("https://picsum.photos/400/400"),
                            FlexImage.UrlImage("https://picsum.photos/401/401"),
                            FlexImage.UrlImage("https://picsum.photos/402/402")
                        )
                    )
                }
            }
        }
    }
}