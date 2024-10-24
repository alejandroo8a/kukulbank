package com.aor.bank.core.presentation.util

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatterUtil {
    fun formatBalance(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "MX"))
        return format.format(amount)
    }
}
