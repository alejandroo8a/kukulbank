package com.aor.bank.core.presentation.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SuccessDialog(
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { /* Prevent dismissal by outside tap */ },
        confirmButton = {
            Button(
                onClick = { onDismiss() }
            ) {
                Text("OK")
            }
        },
        title = { Text("Cuenta creada") },
        text = { Text("Tu cuenta fue creada con éxito") }
    )
}
