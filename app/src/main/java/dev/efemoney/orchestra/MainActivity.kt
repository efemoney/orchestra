package dev.efemoney.orchestra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import dev.efemoney.orchestra.ui.OrchestraTheme
import dev.efemoney.orchestra.ui.Orientation.Horizontal
import dev.efemoney.orchestra.ui.Pager

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      OrchestraTheme {
        Pager(Horizontal, Modifier.fillMaxSize()) {
          Text("Page 1", Modifier.wrapContentSize().background(Color.Red), Color.White)
          Text("Page 2", Modifier.wrapContentSize().background(Color.Blue), Color.White)
          Text("Page 3", Modifier.wrapContentSize().background(Color.Green), Color.White)
        }
      }
    }
  }
}
