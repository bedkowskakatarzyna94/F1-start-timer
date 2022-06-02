package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.viewmodels.RankingViewModel
import com.example.fadinglightsapp.adapters.RankingAdapter
import com.example.fadinglightsapp.data.User
import kotlinx.android.synthetic.main.fragment_ranking.*

class RankingFragment : Fragment() {

    //Firebase
    private val PROFILE_DEBUG = "PROFILE_DEBUG"

    private val rankingVm: RankingViewModel by viewModels<RankingViewModel>()
    private val adapter = RankingAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

    private fun bindUserData(user: User) {
        Log.d(PROFILE_DEBUG, user.toString())
    }


/*     ------------- SQL -------------

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.6:5000")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    private val rankingApi = retrofit.create(RankingService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        CoroutineScope(Dispatchers.Main).launch {
            val res = async(Dispatchers.IO) { rankingApi.getRanking().execute().body() }
            val users = res.await()
            val adapter =
                RankingAdapter(users!!)
            rankingRecyclerView.layoutManager = LinearLayoutManager(activity)
            rankingRecyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

*/

}