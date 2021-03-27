package com.farroos.foodmarket.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.farroos.foodmarket.FoodMarket
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.login.LoginResponse
import com.farroos.foodmarket.ui.MainActivity
import com.farroos.foodmarket.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_signin.*

class SigninFragment : Fragment(), SigninContract.view {

    lateinit var presenter: SigninPresenter
    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)
        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
            val home = Intent(activity, MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }

        initView()
        initDummy()

        btn_signup.setOnClickListener {
            val signup = Intent(activity, AuthActivity::class.java)
            signup.putExtra("page_request", 2)
            startActivity(signup)
        }

        btn_signin.setOnClickListener {

            var email = edt_email.text.toString()
            var password = edt_password.text.toString()

            if (email.isNullOrEmpty()) {
                edt_email.error = "Silahkan masukkan email anda"
                edt_email.requestFocus()
            } else if (password.isNullOrEmpty()) {
                edt_password.error = "Silahkan masukkan password anda"
                edt_password.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }
        }

    }

    private fun initDummy() {
        edt_email.setText("testing@mail.com")
        edt_password.setText("qwertyuiop'")
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {

        FoodMarket.getApp().seToken(loginResponse.accessToken)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        FoodMarket.getApp().seUser(json)

        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
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