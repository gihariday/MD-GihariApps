package com.dicoding.c23_pc713.gihariapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String,
    val image: Int
): Parcelable