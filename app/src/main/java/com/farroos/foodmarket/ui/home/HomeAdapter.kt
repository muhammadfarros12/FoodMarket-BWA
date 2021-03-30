package com.farroos.foodmarket.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.home.Data
import kotlinx.android.synthetic.main.item_home_horizontal.view.*

class HomeAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                txt_title.text = data.name
                data.rate?.let {
                    rb_food.rating = it.toFloat()
                }

                Glide.with(context)
                    .load(data.picturePath)
                    .into(img_poster)

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}