package com.farroos.foodmarket.ui.home

import com.farroos.foodmarket.base.BasePresenter
import com.farroos.foodmarket.base.BaseView
import com.farroos.foodmarket.model.response.home.HomeResponse

interface HomeContract {
    interface View : BaseView {

        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)

    }

    interface Presenter : HomeContract, BasePresenter {

        fun getHome()

    }

}