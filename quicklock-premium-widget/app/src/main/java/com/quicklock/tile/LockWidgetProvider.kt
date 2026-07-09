package com.quicklock.tile

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * A home-screen widget. Tapping it broadcasts ACTION_LOCK back to this
 * provider, which then asks the accessibility service to lock the screen.
 */
class LockWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (id in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_lock)

            val intent = Intent(context, LockWidgetProvider::class.java).apply {
                action = ACTION_LOCK
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

            appWidgetManager.updateAppWidget(id, views)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_LOCK) {
            val service = LockAccessibilityService.instance
            if (service != null) {
                service.lockScreen()
            } else {
                // Accessibility service not running — open the app so it can be enabled.
                val open = Intent(context, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(open)
            }
        }
    }

    companion object {
        const val ACTION_LOCK = "com.quicklock.tile.ACTION_LOCK"
    }
}
