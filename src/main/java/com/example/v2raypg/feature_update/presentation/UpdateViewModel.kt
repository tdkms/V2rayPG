package com.example.v2raypg.feature_update.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.v2raypg.feature_update.domain.use_cases.GetUpdateInfo
import com.example.v2raypg.feature_update.domain.utils.Update
import com.example.v2raypg.feature_update.domain.utils.UpdateAction
import kotlinx.coroutines.launch

class UpdateViewModel : ViewModel() {
    var state by mutableStateOf(Update())

    fun onAction(action: UpdateAction, context: Context) {
        when (action) {
            UpdateAction.Back -> onBackAction()
            UpdateAction.Update -> onUpdateAction(context)
        }
    }

    private fun onBackAction() {}

    private fun onUpdateAction(context: Context) {
        try {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(state.downloadUrl)
            startActivity(context, openUrl, null)
        } catch (e: Exception) {
            Toast.makeText(context, "Download is not available right now.", Toast.LENGTH_LONG)
                .show()
        }
    }

    fun checkAppUpdate(appVersion: Float) {
        viewModelScope.launch {
            val update = GetUpdateInfo()()
            if (update is Update) {
                val updateVersion = update.latestVersion.toFloat()
                Log.d("test", "App version : $appVersion")
                Log.d("test", "Update version : $updateVersion")
                state = update.copy(updateAvailable = appVersion < updateVersion)
            }
        }

    }
}