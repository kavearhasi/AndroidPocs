package com.example.workmanageronetime.data.di


import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class AppLifecycleObserver:DefaultLifecycleObserver {
    var isAppForeground  = false

    override fun onStart(owner:LifecycleOwner){
        super.onStart(owner)
        isAppForeground  = true
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        isAppForeground = false
    }

}