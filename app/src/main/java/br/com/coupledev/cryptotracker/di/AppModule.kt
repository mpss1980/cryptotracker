package br.com.coupledev.cryptotracker.di

import br.com.coupledev.cryptotracker.core.data.networking.HttpClientFactory
import br.com.coupledev.cryptotracker.crypto.data.networking.RemoteCoinRepository
import br.com.coupledev.cryptotracker.crypto.domain.CoinRepository
import br.com.coupledev.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinRepository).bind<CoinRepository>()
    viewModelOf(::CoinListViewModel)
}