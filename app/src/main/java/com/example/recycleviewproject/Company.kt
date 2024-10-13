package com.example.recycleviewproject
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    val name: String,
    val description: String,
    val developer: String,
    val photo: Int
) : Parcelable
