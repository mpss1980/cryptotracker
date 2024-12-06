package br.com.coupledev.cryptotracker.crypto.data.networking

import br.com.coupledev.cryptotracker.core.data.networking.constructUrl
import br.com.coupledev.cryptotracker.core.data.networking.safeCall
import br.com.coupledev.cryptotracker.core.domain.Result
import br.com.coupledev.cryptotracker.core.domain.errors.NetworkError
import br.com.coupledev.cryptotracker.core.domain.map
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import br.com.coupledev.cryptotracker.crypto.data.networking.dto.toCoin
import br.com.coupledev.cryptotracker.crypto.domain.Coin
import br.com.coupledev.cryptotracker.crypto.domain.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinRepository(
    private val httpClient: HttpClient
): CoinRepository {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
           return safeCall<CoinsResponseDto> {
               httpClient.get(
                   urlString = constructUrl("/assets")
               )
           }.map { response ->
               response.data.map { it.toCoin() }
           }
    }
}