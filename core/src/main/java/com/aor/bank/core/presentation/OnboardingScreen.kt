package com.aor.bank.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aor.bank.core.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onSignInClicked: () -> Unit,
    onCreateAccountClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.kukulbank)) },
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFE3F2FD),
                                Color(0xFFBBDEFB)
                            )
                        )
                    ),
                contentAlignment = Alignment.BottomCenter
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(R.string.onboarding_title),
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            lineHeight = 28.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = stringResource(R.string.onboarding_subtitle),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(horizontal = 16.dp),
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = onSignInClicked,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                                .height(56.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.login),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = onCreateAccountClicked,
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                Color.Black
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp)
                                .height(56.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.create_account),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen() {
    OnboardingScreen(
        onSignInClicked = {},
        onCreateAccountClicked = {}
    )
}