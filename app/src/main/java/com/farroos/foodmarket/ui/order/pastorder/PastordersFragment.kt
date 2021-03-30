package com.farroos.foodmarket.ui.order.pastorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.transaction.Data
import com.farroos.foodmarket.ui.order.inprogress.InprogressAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.rcv_list

class PastordersFragment : Fragment(), PastordersAdapter.ItemAdapterCallback {

    private var adapter: PastordersAdapter? = null
    var pastOrdersList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pastOrdersList = arguments?.getParcelableArrayList("data")

        if (!pastOrdersList.isNullOrEmpty()) {
            adapter = PastordersAdapter(pastOrdersList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcv_list.layoutManager = layoutManager
            rcv_list.adapter = adapter

        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, "test klik", Toast.LENGTH_SHORT).show()
    }
}