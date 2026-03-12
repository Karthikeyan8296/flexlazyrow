package io.github.karthikeyan8296.flexlazyrow

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun FlexLazyRow(
    images: List<FlexImage>
) {
    val listState = rememberLazyListState()

    //Images
    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(images.size) { index ->

            //it is the live scroll data - which item is visible, viewport start and end
            val layoutInfo = listState.layoutInfo
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
                when (val image = images[index]) {

                    is FlexImage.PainterImage -> {
                        Image(
                            painter = image.painter,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    translationX = -parallaxOffset
                                    scaleX = 1.5f
                                    scaleY = 1.5f
                                }
                        )
                    }

                    is FlexImage.UrlImage -> {
                        AsyncImage(
                            model = image.url,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    translationX = -parallaxOffset
                                    scaleX = 1.5f
                                    scaleY = 1.5f
                                }
                        )
                    }
                }
            }
        }
    }
}

sealed class FlexImage {
    data class PainterImage(val painter: Painter) : FlexImage()
    data class UrlImage(val url: String) : FlexImage()
}