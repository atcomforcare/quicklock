package com.quicklock.tile

import android.app.PendingIntent
import android.content.Intent
import android.service.quicksettings.TileService

/**
 * The Quick Settings tile. When tapped, it asks the accessibility
 * service to lock the screen. If that service isn't switched on yet,
 * it opens the app so the user can enable it.
 */
class LockTileService : TileService() {

    override fun onClick() {
        super.onClick()

        val service = LockAccessibilityService.instance
        if (service != null) {
            service.lockScreen()
        } else {
            // Accessibility service is off — send the user to the app to turn it on.
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
            startActivityAndCollapse(pendingIntent)
        }
    }
}
