package br.com.coupledev.cryptotracker.crypto.presentation.coin_detail

import android.icu.number.NumberFormatter
import android.icu.text.NumberFormat
import java.util.Locale

data class ValueLabel(
    val value: Float,
    val unit: String
) {
    fun formatted(): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            maximumFractionDigits = when {
                value > 1000 -> 0
                value in 2f..999f -> 2
                else -> 3
            }
            minimumFractionDigits = 0
        }
        return "${formatter.format(value)}$unit"
    }
}
