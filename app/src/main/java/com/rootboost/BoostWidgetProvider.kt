package com.rootboost

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class BoostWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        manager: AppWidgetManager,
        ids: IntArray
    ) {
        ids.forEach { updateWidget(context, manager, it, "Ready") }
    }

    private fun updateWidget(
        context: Context,
        manager: AppWidgetManager,
        id: Int,
        status: String
    ) {
        val views = RemoteViews(context.packageName, R.layout.boost_widget)
        views.setTextViewText(R.id.widgetStatus, status)

        val intent = Intent(context, BoostReceiver::class.java)
        val pending = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widgetButton, pending)
        manager.updateAppWidget(id, views)
    }

    companion object {
        fun updateAll(context: Context, status: String) {
            val manager = AppWidgetManager.getInstance(context)
            val component = ComponentName(context, BoostWidgetProvider::class.java)
            val ids = manager.getAppWidgetIds(component)
            ids.forEach { id ->
                val views = RemoteViews(context.packageName, R.layout.boost_widget)
                views.setTextViewText(R.id.widgetStatus, status)
                val intent = Intent(context, BoostReceiver::class.java)
                val pending = PendingIntent.getBroadcast(
                    context,
                    1001,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widgetButton, pending)
                manager.updateAppWidget(id, views)
            }
        }
    }
}
