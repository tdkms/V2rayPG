package com.example.v2raypg.feature_update.domain.use_cases

import android.util.Log
import com.example.v2raypg.feature_update.domain.utils.Update
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class GetUpdateInfo {
    suspend operator fun invoke(): Update? {
        return try {
            withContext(Dispatchers.IO) {
                val url =
                    URL("https://raw.githubusercontent.com/tdkms/V2rayPG/main/VersionInfo.json")
                val jsonData = url.readText()
                val gson = Gson()
                gson.fromJson(jsonData, Update::class.java)
            }
        } catch (e: Exception) {
            Log.e("test", "Error fetching data: ${e.message}")
            null
        }
    }
}