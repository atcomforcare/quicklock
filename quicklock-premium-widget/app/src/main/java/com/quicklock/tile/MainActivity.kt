package com.quicklock.tile

import android.app.Activity
import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnAccessibility).setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        findViewById<Button>(R.id.btnAddTile).setOnClickListener {
            addTileToQuickSettings()
        }
    }

    private fun addTileToQuickSettings() {
        val statusBar = getSystemService(StatusBarManager::class.java)
        statusBar.requestAddTileService(
            ComponentName(this, LockTileService::class.java),
            getString(R.string.tile_label),
            Icon.createWithResource(this, R.drawable.ic_lock),
            { runnable -> runnable.run() },
            { result ->
                runOnUiThread {
                    Toast.makeText(
                        this,
                        getString(R.string.tile_request_done),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}
