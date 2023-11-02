package com.example.v2raypg.feature_update.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.v2raypg.presentation.Dimens.ELEVATION_SMALL
import com.example.v2raypg.presentation.Dimens.PADDING_SMALL

@Composable
fun UpdateButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(PADDING_SMALL),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        elevation = ButtonDefaults.buttonElevation(ELEVATION_SMALL)
    ) {
        Text(text = text, fontSize = 14.sp, maxLines = 1)
    }
}

@Composable
fun UpdateTextButton(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(text = text, style = MaterialTheme.typography.bodyMedium, maxLines = 1)
    }
}