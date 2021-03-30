package com.farroos.foodmarket.ui.order.pastorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farroos.foodmarket.R
import com.farroos.foodmarket.model.response.transaction.Data
import com.farroos.foodmarket.utils.Helpers.convertLongToTime
import com.farroos.foodmarket.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_pastorders.view.*

class PastordersAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback,
) : RecyclerView.Adapter<PastordersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PastordersAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorders, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PastordersAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                txt_title.text = data.food.name
                txt_price.formatPrice(data.food.price.toString())
                txt_date.text = data.food.createdAt.convertLongToTime("MMM dd, HH.mm")

                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(img_poster)

                if (data.status.equals("CANCELLED", true)){
                    txt_cancelled.visibility = View.VISIBLE
                }

                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}