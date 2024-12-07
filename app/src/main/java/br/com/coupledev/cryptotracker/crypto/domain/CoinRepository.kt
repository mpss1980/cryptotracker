package br.com.coupledev.cryptotracker.crypto.domain

import br.com.coupledev.cryptotracker.core.domain.Result
import br.com.coupledev.cryptotracker.core.domain.errors.NetworkError
import java.time.ZonedDateTime

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}