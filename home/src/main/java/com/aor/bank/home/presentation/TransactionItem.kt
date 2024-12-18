package com.aor.bank.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aor.bank.core.data.model.TransactionModel
import com.aor.bank.core.presentation.util.CurrencyFormatterUtil
import com.aor.bank.core.presentation.theme.Green50
import com.aor.bank.home.R

@Composable
fun TransactionItem(
    transaction: TransactionModel,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = if (transaction.amount >= 0) Green50 else Color.Red
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (transaction.amount >= 0) stringResource(R.string.income) else stringResource(
                    R.string.Spent
                ),
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = CurrencyFormatterUtil.formatBalance(transaction.amount),
            style = MaterialTheme.typography.bodyLarge,
            color = if (transaction.amount >= 0) Green50 else Color.Red
        )
    }
}
