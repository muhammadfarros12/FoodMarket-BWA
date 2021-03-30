package com.farroos.foodmarket.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.farroos.foodmarket.model.response.transaction.Data
import com.farroos.foodmarket.ui.order.inprogress.InprogressFragment
import com.farroos.foodmarket.ui.order.pastorder.PastordersFragment

    class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        var inProgressList: ArrayList<Data>? = ArrayList()
        var pastOrdersList: ArrayList<Data>? = ArrayList()

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "In Progress"
                1 -> "Past Orders"
                else -> ""
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment
            return when (position) {
                0 -> {
                    fragment = InprogressFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", inProgressList)
                    fragment.arguments = bundle
                    return fragment
                }
                1 -> {
                    fragment = PastordersFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", pastOrdersList)
                    fragment.arguments = bundle
                    return fragment
                }
                else -> {
                    fragment = InprogressFragment()
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("data", inProgressList)
                    fragment.arguments = bundle
                    return fragment
                }
            }
        }

        fun setData(
            inprogressFragmentParams: ArrayList<Data>?,
            pastOrderFragmentParams: ArrayList<Data>?,
        ) {
            inProgressList = inprogressFragmentParams
            pastOrdersList = pastOrderFragmentParams
        }

    }