package com.farroos.foodmarket.ui.home.recommended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.dummy.HomeVerticalModel
import com.farroos.foodmarket.model.response.home.Data
import com.farroos.foodmarket.ui.detail.DetailActivity
import com.farroos.foodmarket.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeRecommendedFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallback {

    private var recommendedList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recommendedList = arguments?.getParcelableArrayList("data")

//        initDataDummy()
        var adapter = HomeNewTasteAdapter(recommendedList!!, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcv_list.layoutManager = layoutManager
        rcv_list.adapter = adapter
    }

//    fun initDataDummy() {
//        foodList = ArrayList()
//        foodList.add(HomeVerticalModel("Cherry Health", "10000","", 5f))
//        foodList.add(HomeVerticalModel("Burger Emak", "14500","", 5f))
//        foodList.add(HomeVerticalModel("Bakso Wenak", "20000","", 4.3f))
//    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

}