package br.com.coupledev.cryptotracker.core.domain.errors

import br.com.coupledev.cryptotracker.core.domain.Error

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN,
}