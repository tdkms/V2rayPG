package com.example.v2raypg.feature_update.domain.utils

data class Update(
    val latestVersion: String = "",
    val releaseNotes: List<String> = listOf(),
    val isMandatory: Boolean = false,
    val downloadUrl: String = "",
    val imageUrls: List<String> = listOf(),
    val updateAvailable: Boolean = false
)