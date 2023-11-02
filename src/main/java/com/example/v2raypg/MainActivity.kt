package com.example.v2raypg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.v2raypg.feature_update.presentation.UpdateDialog
import com.example.v2raypg.feature_update.presentation.UpdateViewModel
import com.example.v2raypg.ui.theme.V2rayPGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            V2rayPGTheme {
                val updateViewModel = viewModel<UpdateViewModel>()
                updateViewModel.checkAppUpdate(getAppVersion())

                Box(modifier = Modifier.fillMaxSize()) {
                    // Add Home screen here.
                    // Show the update dialog
                    if (updateViewModel.state.updateAvailable) {
                        UpdateDialog(updateViewModel = updateViewModel, context = this@MainActivity)
                    }
                }
            }
        }
    }

    private fun getAppVersion(): Float {
        val context = applicationContext
        val packageName = context.packageName
        val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
        return packageInfo.versionName.toFloat()
    }
}