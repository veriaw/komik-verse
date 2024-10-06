package com.dicoding.komikverse

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val name:String,
    val genre: String,
    val description: String,
    val photo: String,
    val sinopsis: String
):Parcelable
