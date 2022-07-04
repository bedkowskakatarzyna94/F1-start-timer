package com.example.fadinglightsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.data.User
import kotlinx.android.synthetic.main.ranking_row.view.*

class RankingAdapter(var users: List<User>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val rankingRow = layoutInflater.inflate(R.layout.ranking_row, viewGroup, false)
        return MyViewHolder(rankingRow)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val name = holder.view.user_name
        val points = holder.view.points
        name.text = users[position].name
        points.text = users[position].time.toString()
    }
}

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
