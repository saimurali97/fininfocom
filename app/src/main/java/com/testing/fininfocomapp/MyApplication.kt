package com.testing.fininfocomapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val realmConfig = RealmConfiguration.Builder()
            .name("myrealm.realm")
            .schemaVersion(1)
            .build()

        Realm.setDefaultConfiguration(realmConfig)
    }
}