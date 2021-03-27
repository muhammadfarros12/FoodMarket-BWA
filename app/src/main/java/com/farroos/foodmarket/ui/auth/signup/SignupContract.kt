package com.farroos.foodmarket.ui.auth.signup

import android.net.Uri
import android.view.View
import com.farroos.foodmarket.base.BasePresenter
import com.farroos.foodmarket.base.BaseView
import com.farroos.foodmarket.model.request.RegisterRequest
import com.farroos.foodmarket.model.response.login.LoginResponse

interface SignupContract {
    interface view : BaseView {

        fun onRegisterSuccess(loginResponse: LoginResponse, view: View)
        fun onRegisterPhotoSuccess(view: View)
        fun onRegisterFailed(message: String)

    }

    interface Presenter : SignupContract, BasePresenter {

        fun submitRegister(registerRequest: RegisterRequest, view: View)
        fun submitPhotoRegister(filePath: Uri, view: View)
    }

}