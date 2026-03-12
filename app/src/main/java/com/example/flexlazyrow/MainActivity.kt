package com.example.flexlazyrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flexlazyrow.ui.theme.FlexLazyRowTheme

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
                    FlexLazyRow()
                }
            }
        }
    }
}

@Composable
fun FlexLazyRow() {
    val images = listOf(
        R.drawable.food,
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.photo5,
        R.drawable.photo3,
        R.drawable.food4,
        R.drawable.food,
        R.drawable.food2,
        R.drawable.photo5,
    )

    val listStateHorizontal = rememberLazyListState()


    //Images
    LazyRow(
        state = listStateHorizontal,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(images.size) { index ->

            //it is the live scroll data - which item is visible, viewport start and end
            val layoutInfo = listStateHorizontal.layoutInfo
            val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull {
                it.index == index
            }

            // center item - more movement, edges - less movement
            val parallaxOffset = if (itemInfo != null) {
                val viewportCenter =
                    (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2

                val itemCenter = itemInfo.offset + itemInfo.size / 2
                (itemCenter - viewportCenter) * 0.15f
            } else 0f

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(images[index]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            //movement
                            translationX = -parallaxOffset
                            //scale
                            scaleX = 1.5f
                            scaleY = 1.5f
                        }
                )
            }
        }
    }
}