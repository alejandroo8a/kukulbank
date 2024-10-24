package com.aor.bank.sign_up.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.aor.bank.sign_up.R

@Composable
fun UserInputFields(
    name: String,
    lastName: String,
    email: String,
    password: String,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit
) {
    TextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(stringResource(R.string.name)) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = lastName,
        onValueChange = onLastNameChange,
        label = { Text(stringResource(R.string.last_name)) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(stringResource(R.string.email)) },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    TextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(stringResource(R.string.password)) },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}
