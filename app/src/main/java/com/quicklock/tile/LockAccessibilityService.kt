package com.quicklock.tile

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

/**
 * This service exists only so the app is allowed to lock the screen.
 * Android lets an Accessibility Service call GLOBAL_ACTION_LOCK_SCREEN,
 * which is the same action the power button uses. We don't read or
 * process any screen content — onAccessibilityEvent is intentionally empty.
 */
class LockAccessibilityService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Not used. We never inspect events.
    }

    override fun onInterrupt() {
        // Not used.
    }

    override fun onDestroy() {
        super.onDestroy()
        if (instance === this) {
            instance = null
        }
    }

    /** Locks the screen. Returns true if the action was accepted. */
    fun lockScreen(): Boolean {
        return performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)
    }

    companion object {
        @Volatile
        var instance: LockAccessibilityService? = null
    }
}
