package br.com.coupledev.cryptotracker.crypto.presentation.coin_list

import br.com.coupledev.cryptotracker.core.domain.errors.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}