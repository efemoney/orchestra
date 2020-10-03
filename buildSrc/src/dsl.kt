import org.gradle.api.Action
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.plugins.PluginManager

fun PluginManager.withAnyPlugin(vararg pluginIds: String, action: Action<AppliedPlugin>) =
  pluginIds.forEach { withPlugin(it, action) }