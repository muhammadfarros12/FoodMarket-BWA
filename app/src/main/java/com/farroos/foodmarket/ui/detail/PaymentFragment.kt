package com.farroos.foodmarket.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.farroos.foodmarket.FoodMarket
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.checkout.CheckoutResponse
import com.farroos.foodmarket.model.response.home.Data
import com.farroos.foodmarket.model.response.login.User
import com.farroos.foodmarket.utils.Helpers.formatPrice
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : Fragment(), PaymentContract.view {

    var progressDialog: Dialog? = null
    lateinit var presenter: PaymentPresenter
    var total: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        var data = arguments?.getParcelable<Data>("data")
        initView(data)
        initView()
        presenter = PaymentPresenter(this)

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

    private fun initView(data: Data?) {
        txt_title.text = data?.name
        txt_price.formatPrice(data?.price.toString())

        Glide.with(this)
            .load(data?.picturePath)
            .into(img_poster)

        txt_name_item.text = data?.name
        txt_harga.formatPrice(data?.price.toString())

        txt_price.text = "IDR. 0"
        txt_total.text = "IDR. 0"
        txt_tax.text = "IDR. 0"
        total = 0

        data?.price?.let {
            val price = it.toInt()
            var totalTax = price.div(10)
            txt_tax.formatPrice(totalTax.toString())

            total = price + totalTax + 5000
            txt_total.formatPrice(total.toString())
        }

        var user = FoodMarket.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        txt_nama.text = userResponse?.name
        txt_phone_no.text = userResponse?.phoneNumber
        txt_Address.text = userResponse?.address
        txt_city.text = userResponse?.city

        btn_checkout.setOnClickListener {
            presenter.getCheckout(
                data?.id.toString(),
                userResponse?.id.toString(),
                "1",
                total.toString(),
                it
            )
        }

    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {

        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)
        Navigation.findNavController(view)
            .navigate(R.id.action_paymentFragment_to_paymentSuccessFragment)
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}