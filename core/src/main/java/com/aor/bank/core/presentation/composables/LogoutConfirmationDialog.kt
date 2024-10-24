package com.aor.bank.core.presentation.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aor.bank.core.R

@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(R.string.dialog_logout_title), style = MaterialTheme.typography.headlineSmall)
        },
        text = {
            Text(text = stringResource(R.string.dialog_logout_description))
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(stringResource(R.string.dialog_logout_confirm))
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text(stringResource(R.string.dialog_logout_cancel))
            }
        }
    )
}