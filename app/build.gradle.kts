import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
  android()
  kotlin("android")
}

android {
  defaultConfig.applicationId = "dev.efemoney.orchestra"
  buildFeatures.compose = true

  composeOptions {
    kotlinCompilerVersion = Versions.kotlin
    kotlinCompilerExtensionVersion = Versions.compose
  }
}

tasks.withType<KotlinCompile<*>>().configureEach {
  kotlinOptions {
    freeCompilerArgs = freeCompilerArgs + listOf(
      "-Xskip-prerelease-check",
      "-Xallow-jvm-ir-dependencies"
    )
    if (this is KotlinJvmOptions) {
      useIR = true
      jvmTarget = "1.8"
    }
  }
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))

  implementation(Deps.androidx.core)
  implementation(Deps.androidx.appcompat)

  implementation(Deps.ui.tooling)
  implementation(Deps.compose.ui)
  implementation(Deps.compose.material)

  implementation(Deps.material)
}