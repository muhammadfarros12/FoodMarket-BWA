package com.farroos.foodmarket.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.home.Data
import com.farroos.foodmarket.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    var bundle: Bundle ?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data?.let {
                initView(it)
            }
        }

        btn_order_now.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_paymentFragment, bundle)
        }

    }

    private fun initView(data: Data?) {

        bundle = bundleOf("data" to data)

        Glide.with(requireActivity())
                .load(data?.picturePath)
                .into(img_poster)

        txt_title.text = data?.name
        txt_desc.text = data?.description
        txt_ingredients.text = data?.ingredients

        txt_total.formatPrice(data?.price.toString())

    }

}