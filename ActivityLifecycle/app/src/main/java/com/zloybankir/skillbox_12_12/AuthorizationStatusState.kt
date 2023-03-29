package com.zloybankir.skillbox_12_12

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorizationStatusState(
    val message: String
) : Parcelable