package dev.efemoney.orchestra.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp

@Composable
val Float.inDp: Dp
  get() = with(DensityAmbient.current) { toDp() }
