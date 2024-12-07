package br.com.coupledev.cryptotracker.crypto.data.networking

import br.com.coupledev.cryptotracker.core.data.networking.constructUrl
import br.com.coupledev.cryptotracker.core.data.networking.safeCall
import br.com.coupledev.cryptotracker.core.domain.Result
import br.com.coupledev.cryptotracker.core.domain.errors.NetworkError
import br.com.coupledev.cryptotracker.core.domain.map
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.CoinHistoryResponseDto
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.toCoin
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.toCoinPrice
import br.com.coupledev.cryptotracker.crypto.domain.Coin
import br.com.coupledev.cryptotracker.crypto.domain.CoinPrice
import br.com.coupledev.cryptotracker.crypto.domain.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinRepository(
    private val httpClient: HttpClient
) : CoinRepository {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startInMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endInMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        return safeCall<CoinHistoryResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startInMillis)
                parameter("end", endInMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}