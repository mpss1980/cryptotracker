package br.com.coupledev.cryptotracker.crypto.data.networking.dto

import br.com.coupledev.cryptotracker.crypto.domain.CoinPrice
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId

@Serializable
data class CoinPriceDto(
    val priceUsd: Double,
    val time: Long
) {

}

fun CoinPriceDto.toCoinPrice() : CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
       dateTime = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault())
    )
}
