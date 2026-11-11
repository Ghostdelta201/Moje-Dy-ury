package pl.grafik.pracy

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object Alarms {
    private const val PREF = "alarms"

    private fun pending(ctx: Context, id: String, title: String, text: String): PendingIntent {
        val i = Intent(ctx, AlarmReceiver::class.java)
        i.action = id
        i.putExtra("id", id).putExtra("t", title).putExtra("x", text)
        return PendingIntent.getBroadcast(
            ctx, 0, i, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun schedule(ctx: Context, id: String, at: Long, title: String, text: String) {
        if (at <= System.currentTimeMillis()) return
        val am = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val show = PendingIntent.getActivity(
            ctx, 0, Intent(ctx, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
        am.setAlarmClock(AlarmManager.AlarmClockInfo(at, show), pending(ctx, id, title, text))
        ctx.getSharedPreferences(PREF, 0).edit().putString(id, "$at|$title|$text").apply()
    }

    fun cancel(ctx: Context, id: String) {
        val am = ctx.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pi = pending(ctx, id, "", "")
        am.cancel(pi)
        pi.cancel()
        ctx.getSharedPreferences(PREF, 0).edit().remove(id).apply()
    }

    fun restore(ctx: Context) {
        val prefs = ctx.getSharedPreferences(PREF, 0)
        for ((id, v) in prefs.all) {
            val p = (v as? String)?.split("|", limit = 3) ?: continue
            if (p.size < 3) continue
            val at = p[0].toLongOrNull() ?: continue
            if (at > System.currentTimeMillis()) schedule(ctx, id, at, p[1], p[2])
            else prefs.edit().remove(id).apply()
        }
    }
}
