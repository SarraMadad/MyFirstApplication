package com.example.myfirstapplication

import android.app.Application
import android.content.Context

class DungeonApplication : Application() {

    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}