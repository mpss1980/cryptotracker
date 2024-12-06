package br.com.coupledev.cryptotracker.crypto.presentation.coin_list

import br.com.coupledev.cryptotracker.crypto.presentation.models.CoinUi

interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}