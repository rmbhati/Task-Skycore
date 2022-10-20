package com.kgk.task.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kgk.task.R
import com.kgk.task.databinding.RowHomeBinding
import kotlinx.android.synthetic.main.row_home.view.*

class HomeAdapter(
    private val context: Context, private val dataList: List<BusinessesData>,
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    class ViewHolder(itemView: RowHomeBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.row_home, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val home = dataList[position]

        Glide.with(context).load(home.image_url)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.itemView.img)
        holder.itemView.name.text = home.name
        if (home.location.display_address.size >= 2) {
            holder.itemView.address.text =
                home.location.display_address[0] + "\n" + home.location.display_address[1]
        } else {
            holder.itemView.address.text = home.location.display_address.toString()
        }
        if (home.is_closed) {
            holder.itemView.sts.text = "Now Closed"
        } else {
            holder.itemView.sts.text = "Now Open"
        }
        holder.itemView.review.text = "${home.rating}"

    }

    override fun getItemCount(): Int {
        return dataList.size

    }
}