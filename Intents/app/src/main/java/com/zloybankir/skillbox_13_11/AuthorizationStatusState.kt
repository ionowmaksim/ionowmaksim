package com.zloybankir.skillbox_13_11

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorizationStatusState(
    val message: String
) : Parcelable