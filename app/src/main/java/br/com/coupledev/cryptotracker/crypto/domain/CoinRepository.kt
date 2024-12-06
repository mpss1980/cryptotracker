package br.com.coupledev.cryptotracker.crypto.domain

import br.com.coupledev.cryptotracker.core.domain.Result
import br.com.coupledev.cryptotracker.core.domain.errors.NetworkError

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}