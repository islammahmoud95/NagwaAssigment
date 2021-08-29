package com.stech.nagwaassignment.entities

import android.provider.SyncStateContract
import com.stech.nagwaassignment.common.NOTHINGSTATUES

data class AttachedFiles(
    val id: String,
    val name: String,
    val type: String,
    val url: String,
    var statues: String= NOTHINGSTATUES
)
