package com.example.v2raypg.feature_update.presentation

import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.v2raypg.feature_update.domain.utils.UpdateAction
import com.example.v2raypg.feature_update.presentation.components.UpdateButton
import com.example.v2raypg.feature_update.presentation.components.UpdateTextButton
import com.example.v2raypg.feature_update.presentation.slider.UpdateSlider
import com.example.v2raypg.presentation.Dimens.ELEVATION_LARGE
import com.example.v2raypg.presentation.Dimens.ELEVATION_SMALL
import com.example.v2raypg.presentation.Dimens.PADDING_MEDIUM
import com.example.v2raypg.presentation.Dimens.PADDING_SMALL

@Composable
fun UpdateDialog(updateViewModel: UpdateViewModel, context: Context) {
    val scrollState = rememberScrollState()
    var isDialogOpen by remember { mutableStateOf(true) }

    if (isDialogOpen) {
        Dialog(onDismissRequest = {
            if (!updateViewModel.state.isMandatory) isDialogOpen = false
        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight(0.8f),
                shape = RoundedCornerShape(ELEVATION_LARGE),
                elevation = CardDefaults.cardElevation(ELEVATION_SMALL)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    UpdateSlider(modifier = Modifier.fillMaxHeight(0.4f), updateViewModel)
                    DialogContent(
                        modifier = Modifier.weight(1f),
                        updateViewModel.state.releaseNotes,
                        scrollState
                    )
                    // Dialog buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(PADDING_MEDIUM),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (!updateViewModel.state.isMandatory) {
                            UpdateTextButton(text = "later") {
                                isDialogOpen = false
                                updateViewModel.onAction(UpdateAction.Back, context = context)
                            }
                        }
                        UpdateButton(text = "Update now") {
                            updateViewModel.onAction(UpdateAction.Update, context)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DialogContent(modifier: Modifier, releaseNote: List<String>, scrollState: ScrollState) {
    Column(
        modifier = modifier.padding(
            start = PADDING_SMALL, end = PADDING_SMALL, top = PADDING_SMALL
        ), verticalArrangement = Arrangement.spacedBy(PADDING_MEDIUM)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "New Update found",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = "Release notes :",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Text(
                text = releaseNote.joinToString("\n* "),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.verticalScroll(scrollState)
            )
        }
    }
}
