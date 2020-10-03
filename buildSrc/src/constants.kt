@file:Suppress("unused")

object Versions {
  const val agp = "4.2.0-alpha13"
  const val kotlin = "1.4.10"
  const val compose = "1.0.0-alpha04"
  const val material = "1.2.1"
}

object Deps {

  const val material = "com.google.android.material:material:${Versions.material}"

  object androidx {
    const val core = "androidx.core:core-ktx:1.3.1"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
  }

  object compose {
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
  }

  object ui {
    const val tooling = "androidx.ui:ui-tooling:${Versions.compose}"
  }
}
