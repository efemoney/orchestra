@file:Suppress("NOTHING_TO_INLINE")

import org.gradle.plugin.use.PluginDependenciesSpec

inline fun PluginDependenciesSpec.android(plugin: String = "application") = id("com.android.$plugin")