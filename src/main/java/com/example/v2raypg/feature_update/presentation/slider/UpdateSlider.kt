package com.example.v2raypg.feature_update.presentation.slider

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.v2raypg.R
import com.example.v2raypg.feature_update.presentation.UpdateViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UpdateSlider(modifier: Modifier, viewModel: UpdateViewModel) {
    val images = viewModel.state.imageUrls
    val pagerState = rememberPagerState(
        initialPage = 0, initialPageOffsetFraction = 0f
    ) { images.count() }

    // TODO: Fix bug when user hold the slider, The loop will get stopped!
    LaunchedEffect(Unit) {
        while (true) {
            delay(3500)
            if (!pagerState.isScrollInProgress) {
                pagerState.animateScrollToPage(
                    page = if (pagerState.canScrollForward) pagerState.currentPage + 1 else pagerState.initialPage
                )
            }
        }
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Box(modifier.fillMaxWidth()) {
            HorizontalPager(state = pagerState) { index ->
                val url = images[index]

                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .error(R.drawable.notfound)
                        .build(),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(5.dp),
                            color = Color.Black,
                            strokeWidth = 1.dp
                        )
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            UpdateSliderIndicator(
                modifier = Modifier.align(Alignment.BottomCenter),
                pagesSize = images.size,
                selectedPage = pagerState.currentPage
            )
        }
    }
}