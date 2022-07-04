package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.adapters.RankingAdapter
import com.example.fadinglightsapp.viewmodels.RankingViewModel
import kotlinx.android.synthetic.main.fragment_ranking.*

class RankingFragment : Fragment() {

    private val rankingVm: RankingViewModel by viewModels()
    private val adapter = RankingAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rankingRecyclerView.layoutManager = LinearLayoutManager(activity)
        rankingRecyclerView.adapter = adapter
        rankingVm.users.observe(viewLifecycleOwner) { users ->
            adapter.users = users
            adapter.notifyDataSetChanged()
        }
    }
}
