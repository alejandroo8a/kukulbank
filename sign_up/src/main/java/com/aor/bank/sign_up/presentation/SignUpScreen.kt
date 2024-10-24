package com.aor.bank.sign_up.presentation

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aor.bank.core.data.model.BaseState
import com.aor.bank.core.presentation.composables.PhotoPicker
import com.aor.bank.core.presentation.composables.SuccessDialog
import com.aor.bank.core.presentation.theme.BankTheme
import com.aor.bank.sign_up.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onBackButton: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_account)) },
                navigationIcon = {
                    IconButton(onClick = onBackButton) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 64.dp)
                ) {
                    UserInputFields(
                        name = name,
                        lastName = lastName,
                        email = email,
                        password = password,
                        onNameChange = { name = it },
                        onLastNameChange = { lastName = it },
                        onEmailChange = { email = it },
                        onPasswordChange = { password = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PhotoPicker(
                        photoUri = photoUri,
                        onPhotoSelected = { uri ->
                            if (uri != null) {
                                photoUri = uri
                            }
                        },
                        context = context,
                        onError = { errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    when (uiState) {
                        is BaseState.Loading -> CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        is BaseState.Success -> {
                            showSuccessDialog = true
                        }
                        is BaseState.Error -> {
                            Text(
                                text = (uiState as BaseState.Error).message,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        else -> {}
                    }
                }

                Button(
                    onClick = {
                        viewModel.signUp(name, lastName, email, password, photoUri)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(56.dp),
                    enabled = uiState !is BaseState.Loading
                ) {
                    Text(stringResource(R.string.create_account))
                }
            }
            if (showSuccessDialog) {
                SuccessDialog(
                    onDismiss = {
                        showSuccessDialog = false
                        onSignUpSuccess.invoke()
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    BankTheme {
        SignUpScreen(
            onSignUpSuccess = {},
            onBackButton = {},
            viewModel = hiltViewModel()
        )
    }
}