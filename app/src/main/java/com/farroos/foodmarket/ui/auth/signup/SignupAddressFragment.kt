package com.farroos.foodmarket.ui.auth.signup

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.farroos.foodmarket.FoodMarket
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.request.RegisterRequest
import com.farroos.foodmarket.model.response.login.LoginResponse
import com.farroos.foodmarket.ui.MainActivity
import com.farroos.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignupContract.view {

    private lateinit var data: RegisterRequest
    lateinit var presenter: SignupPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignupPresenter(this)

        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()

    }

    private fun initListener() {

        btn_signup_now.setOnClickListener {
            
            var phone = edt_phone_number.text.toString()
            var address = edt_address.text.toString()
            var houseNumber = edt_house_number.text.toString()
            var city = edt_city.text.toString()

            data.let {
                it.address = address
                it.city = city
                it.houseNumber = houseNumber
                it.phoneNumber = phone
            }

            if (phone.isNullOrEmpty()) {
                edt_phone_number.error = "Silahkan masukkan nomor phone ande"
                edt_phone_number.requestFocus()
            } else if (address.isNullOrEmpty()) {
                edt_address.error = "Silahkan masukkan house number anda"
                edt_address.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                edt_house_number.error = "Silahkan masukkan nama city anda"
                edt_house_number.requestFocus()
            } else {/*
                presenter.submitRegister(data, it)*/
                presenter.submitPhotoRegister(data.filepath!!, it)
            }
        }
    }

    private fun initDummy() {
        edt_phone_number.setText("022181787864")
        edt_address.setText("Jalan Raya")
        edt_house_number.setText("34")
        edt_city.setText("Batam")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) {
        FoodMarket.getApp().seToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().seUser(json)

        if (data.filepath == null) {
            Navigation.findNavController(view)
                    .navigate(R.id.action_fragmentSignUpAddress_to_signupSuccessFragment, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filepath!!, view)
        }
    }

    override fun onRegisterPhotoSuccess(view: View) {
        Navigation.findNavController(view)
                .navigate(R.id.action_fragmentSignUpAddress_to_signupSuccessFragment, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}