// SPDX-License-Identifier: GPL-3.0-only
package com.rosan.installer.ui.tile

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.quicksettings.TileService
import com.rosan.installer.ui.activity.SettingsActivity

class SettingsTileService : TileService() {

    override fun onClick() {
        super.onClick()

        val intent = Intent(this, SettingsActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) 
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // Android 14 (API 34) 及以上：必须使用 PendingIntent
            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            startActivityAndCollapse(pendingIntent)
        } else {
            // Android 13 及以下：直接使用 Intent
            @Suppress("DEPRECATION")
            startActivityAndCollapse(intent)
        }
    }
}
