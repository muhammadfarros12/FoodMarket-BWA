package com.farroos.foodmarket.ui.order.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.transaction.Data
import kotlinx.android.synthetic.main.fragment_inprogress.*

class InprogressFragment : Fragment(), InprogressAdapter.ItemAdapterCallback {

    private var adapter: InprogressAdapter? = null
    var inProgressList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inProgressList = arguments?.getParcelableArrayList("data")

        if (!inProgressList.isNullOrEmpty()) {
            adapter = InprogressAdapter(inProgressList!!, this)
            val layoutManager = LinearLayoutManager(activity)
            rcv_list.layoutManager = layoutManager
            rcv_list.adapter = adapter

        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, "test klik", Toast.LENGTH_SHORT).show()
    }

}