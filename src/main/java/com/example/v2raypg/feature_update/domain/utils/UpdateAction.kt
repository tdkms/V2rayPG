package com.example.v2raypg.feature_update.domain.utils

sealed class UpdateAction {
    object Update: UpdateAction()
    object Back: UpdateAction()
}
