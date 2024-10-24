package com.aor.bank.sign_up.presentation

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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aor.bank.core.data.model.BaseState
import com.aor.bank.core.ui.theme.BankTheme
import com.aor.bank.sign_up.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_account)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 64.dp)
                ) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(stringResource(R.string.name)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text(stringResource(R.string.last_name)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(R.string.email)) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    when (uiState) {
                        is BaseState.Loading -> CircularProgressIndicator()
                        is BaseState.Success -> {
                            navController.navigate("home") {
                                popUpTo("onboarding") { inclusive = true }
                            }
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
                        viewModel.signUp(name, lastName, email, password)
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(56.dp)
                ) {
                    Text(stringResource(R.string.create_account))
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()

    BankTheme {
        SignUpScreen(
            navController = navController,
            viewModel = hiltViewModel()
        )
    }
}