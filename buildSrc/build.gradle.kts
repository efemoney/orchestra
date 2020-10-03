plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

sourceSets.main.get().java.srcDir("src")

repositories {
  google()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))

  // Plugin dependencies
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
  implementation("com.android.tools.build:gradle:4.2.0-alpha13")
}
