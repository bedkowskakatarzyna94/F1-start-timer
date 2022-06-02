package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fadinglightsapp.viewmodels.GameViewModel
import com.example.fadinglightsapp.R
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*
import kotlin.random.Random.Default.nextInt

class GameFragment : Fragment() {
    private val gameVm: GameViewModel by viewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var current: GregorianCalendar? = null
        val handler = Handler(Looper.myLooper()!!)
        start_button.setOnClickListener {
            start_button.visibility = View.INVISIBLE
            stop_button.visibility = View.VISIBLE
            val delayed = nextInt(5500, 8000).toLong()
            handler.postDelayed(
                {
                    greylights1.setImageResource(R.drawable.red_lights)
                },
                1000
            )
            handler.postDelayed(
                {
                    greylights2.setImageResource(R.drawable.red_lights)
                },
                2000
            )
            handler.postDelayed(
                {
                    greylights3.setImageResource(R.drawable.red_lights)
                },
                3000
            )
            handler.postDelayed(
                {
                    greylights4.setImageResource(R.drawable.red_lights)
                },
                4000
            )
            handler.postDelayed(
                {
                    greylights5.setImageResource(R.drawable.red_lights)
                },
                5000
            )
            handler.postDelayed(
                {
                    greylights1.setImageResource(R.drawable.grey_lights)
                    greylights2.setImageResource(R.drawable.grey_lights)
                    greylights3.setImageResource(R.drawable.grey_lights)
                    greylights4.setImageResource(R.drawable.grey_lights)
                    greylights5.setImageResource(R.drawable.grey_lights)
                    current = GregorianCalendar()
                },
                delayed
            )
        }

        stop_button.setOnClickListener {
            val current2 = GregorianCalendar()
            if (current == null) {
                time.text = "FALSE START"
                handler.removeCallbacksAndMessages(null)
            } else {
                val result =
                    ((((current2.timeInMillis - current!!.timeInMillis)).toDouble()) / 1000)
                time.text = "%.3f".format(result)
                gameVm.updateTime(result)
            }
            current = null
            time.visibility = TextView.VISIBLE
            stop_button.visibility = View.INVISIBLE
            try_again_button.visibility = View.VISIBLE

        }

        try_again_button.setOnClickListener {
            try_again_button.visibility = View.INVISIBLE
            time.visibility = TextView.INVISIBLE
            start_button.visibility = View.VISIBLE
            greylights1.setImageResource(R.drawable.grey_lights)
            greylights2.setImageResource(R.drawable.grey_lights)
            greylights3.setImageResource(R.drawable.grey_lights)
            greylights4.setImageResource(R.drawable.grey_lights)
            greylights5.setImageResource(R.drawable.grey_lights)
        }

    }
}
