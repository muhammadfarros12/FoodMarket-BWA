package com.farroos.foodmarket.ui.auth.signin

import com.farroos.foodmarket.base.BasePresenter
import com.farroos.foodmarket.base.BaseView
import com.farroos.foodmarket.model.response.login.LoginResponse

interface SigninContract {
    interface view : BaseView {

        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)

    }

    interface Presenter : SigninContract, BasePresenter {

        fun submitLogin(email: String, password: String)

    }

}