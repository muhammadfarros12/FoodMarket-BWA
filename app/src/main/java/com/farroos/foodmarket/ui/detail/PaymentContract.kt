package com.farroos.foodmarket.ui.detail

import android.view.View
import com.farroos.foodmarket.base.BasePresenter
import com.farroos.foodmarket.base.BaseView
import com.farroos.foodmarket.model.response.checkout.CheckoutResponse

interface PaymentContract {
    interface view : BaseView {

        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View)
        fun onCheckoutFailed(message: String)

    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCheckout(
            foodId: String,
            userId: String,
            quantity: String,
            total: String,
            view: View
        )
    }
}