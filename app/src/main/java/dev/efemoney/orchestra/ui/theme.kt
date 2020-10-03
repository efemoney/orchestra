package dev.efemoney.orchestra.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import dev.efemoney.orchestra.R

val airbnbCerealBook = fontFamily(
  font(R.font.airbnb_cereal_book)
)

val ttNormsPro = fontFamily(
  font(R.font.tt_norms_pro_bold, FontWeight.Bold)
)

@Composable
fun OrchestraTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colors = lightColors(),
    typography = Typography(defaultFontFamily = airbnbCerealBook),
    content = content
  )
}