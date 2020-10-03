package dev.efemoney.orchestra.ui

import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.TargetAnimation
import androidx.compose.animation.core.tween
import androidx.compose.foundation.animation.AndroidFlingDecaySpec
import androidx.compose.foundation.animation.FlingConfig
import androidx.compose.foundation.gestures.ScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.util.fastMap
import dev.efemoney.orchestra.ui.Orientation.Horizontal
import dev.efemoney.orchestra.ui.Orientation.Vertical
import kotlin.math.ceil
import kotlin.math.floor
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation as ExternalOrientation

@Composable
fun Pager(
  orientation: Orientation,
  modifier: Modifier = Modifier,
  children: @Composable () -> Unit = emptyContent()
) {
  val pagerState = rememberPagerState(orientation)

  Layout(
    children,
    modifier
      .scrollable(pagerState.orientation, pagerState.scrollController)
      .(orientation.offsetFn)(pagerState.scrollOffset.inDp)
  ) { measurables, constraints ->

    val placeables = measurables.fastMap { it.measure(constraints) }

    val measuredSize = placeables.fold(IntSize.Zero) { currentMax, placeable ->
      IntSize(
        width = maxOf(currentMax.width, placeable.width),
        height = maxOf(currentMax.height, placeable.height)
      )
    }

    pagerState.pageCount = placeables.size
    pagerState.pageDimen = if (orientation == Horizontal) measuredSize.width else measuredSize.height

    layout(measuredSize.width, measuredSize.height) {

      placeables.fastForEachIndexed { index, placeable ->
        val pageOffset = pagerState.pageDimen * index
        val pageDx = if (orientation == Horizontal) pageOffset else 0
        val pageDy = if (orientation == Vertical) pageOffset else 0

        placeable.placeRelative(
          pageDx + (measuredSize.width - placeable.width) / 2,
          pageDy + (measuredSize.height - placeable.height) / 2,
        )
      }
    }
  }
}

enum class Orientation(val actual: ExternalOrientation, inline val offsetFn: Modifier.(offset: Dp) -> Modifier) {
  Horizontal(ExternalOrientation.Horizontal, {
    offset(x = it)
  }),
  Vertical(ExternalOrientation.Vertical, {
    offset(y = it)
  });
}

@Composable
private fun rememberPagerState(orientation: Orientation): PagerState {
  val density = DensityAmbient.current
  val clock = AnimationClockAmbient.current.asDisposableClock()

  return remember(orientation, density, clock) { PagerState(orientation, density, clock) }
}

@Stable
private data class PagerState(
  val internalOrientation: Orientation,
  val density: Density,
  val clock: AnimationClockObservable
) {

  val orientation get() = internalOrientation.actual

  var scrollOffset by mutableStateOf(0f)
  var pageDimen by mutableStateOf(0)
  var pageCount by mutableStateOf(0)

  val maxPageFraction get() = pageCount - 1f
  val currentPageFraction get() = scrollOffset / pageDimen

  val scrollController = ScrollableController(
    consumeScrollDelta = { scrollDelta ->
      val oldOffset = scrollOffset
      scrollOffset = (oldOffset + scrollDelta).coerceIn(maxPageFraction * -pageDimen, 0f)
      scrollOffset - oldOffset
    },
    flingConfig = FlingConfig(AndroidFlingDecaySpec(density)) { target ->

      val newTarget = if (target < scrollOffset) {
        floor(currentPageFraction) * pageDimen
      } else {
        ceil(currentPageFraction) * pageDimen
      }.coerceIn(maxPageFraction * -pageDimen, 0f)

      TargetAnimation(newTarget, tween())
    },
    animationClock = clock
  )
}
