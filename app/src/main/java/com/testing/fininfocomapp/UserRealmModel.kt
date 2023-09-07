package com.testing.fininfocomapp

import io.realm.RealmObject

open class UserRealmModel : RealmObject() {
    var name: String = ""
    var age: Int = 0
    var city: String = ""
}
