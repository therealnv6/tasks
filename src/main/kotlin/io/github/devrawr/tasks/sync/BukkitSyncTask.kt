package io.github.devrawr.tasks.sync

import io.github.devrawr.tasks.Task
import io.github.devrawr.tasks.Tasks
import io.github.devrawr.tasks.cancellable.type.BukkitCancellable
import org.bukkit.plugin.java.JavaPlugin

object BukkitSyncTask : Task<BukkitCancellable>
{
    private val plugin: JavaPlugin = Tasks.plugin as JavaPlugin
    private val scheduler = plugin.server.scheduler

    override fun repeating(delay: Long, period: Long, action: () -> Unit): BukkitCancellable
    {
        return BukkitCancellable(
            scheduler.scheduleSyncRepeatingTask(this.plugin, action, delay, period)
        )
    }

    override fun delay(delay: Long, action: () -> Unit): BukkitCancellable
    {
        return BukkitCancellable(
            scheduler.runTaskLater(this.plugin, action, delay)
        )
    }

    override fun call(action: () -> Unit): BukkitCancellable
    {
        return BukkitCancellable(
            scheduler.runTask(this.plugin, action)
        )
    }
}