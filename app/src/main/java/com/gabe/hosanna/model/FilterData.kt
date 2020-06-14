package com.gabe.hosanna.model

data class FilterData(
    val id: String,
    val avatar: String,
    val fullName: String,
    val createdAt: String,
    val gender: String,
    val countries: ArrayList<String>,
    val colors: ArrayList<String>
)