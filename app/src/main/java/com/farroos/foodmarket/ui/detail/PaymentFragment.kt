package com.farroos.foodmarket.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.farroos.foodmarket.R
import kotlinx.android.synthetic.main.fragment_payment.*

    class PaymentFragment : Fragment() {

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

            btn_checkout.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_paymentFragment_to_paymentSuccessFragment)
            }
        }

    }