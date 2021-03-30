package com.farroos.foodmarket.ui.order

import com.farroos.foodmarket.base.BasePresenter
import com.farroos.foodmarket.base.BaseView
import com.farroos.foodmarket.model.response.home.HomeResponse
import com.farroos.foodmarket.model.response.transaction.TransactionResponse

interface OrderContract {
    interface View : BaseView {

        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)

    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()

    }

}