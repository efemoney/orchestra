import com.android.build.gradle.BaseExtension

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
}

subprojects {

  pluginManager.withAnyPlugin("android", "android-library") {
    configure<BaseExtension> {
      compileSdkVersion(30)

      defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
      }

      compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
      }
    }
  }
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}