package com.example.mingseventsapp.model.user

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

data class User(
    var user_id: Int = 0,
    var name: String = "",
    var second_name: String = "",
    var phone: Int = 0,
    var password: String = "",
    val photo: String = "",
    var email: String = "",
    var role_id: Int = 7
               )
