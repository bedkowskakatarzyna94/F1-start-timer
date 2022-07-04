package com.example.fadinglightsapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fadinglightsapp.R
import com.example.fadinglightsapp.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*
import kotlin.random.Random.Default.nextLong

class GameFragment : Fragment() {
    private val gameVm: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        val lights: List<ImageView> =
            listOf(greylights1, greylights2, greylights3, greylights4, greylights5)

        var startTime: GregorianCalendar? = null
        val handler = Looper.myLooper()?.let { Handler(it) }

        start_button.setOnClickListener {
            start_button.visibility = View.INVISIBLE
            stop_button.visibility = View.VISIBLE
            lights.forEachIndexed { index, imageView ->
                handler?.postDelayed(
                    { imageView.setImageResource(R.drawable.red_lights) },
                    (index + 1) * 1000L
                )
                handler?.postDelayed({
                    lights.forEach { light ->
                        light.setImageResource(R.drawable.grey_lights)
                        startTime = GregorianCalendar()
                    }
                }, nextLong(5500, 8000))
            }
        }

        stop_button.setOnClickListener {
            handler?.removeCallbacksAndMessages(null)
            val stopTime = GregorianCalendar()

            if (startTime == null) {
                time.text = getText(R.string.false_start_text)
            } else {
                val resultTime =
                    (stopTime.timeInMillis - startTime!!.timeInMillis).toDouble() / 1000
                time.text = "%.3f".format(resultTime)
                gameVm.updateTime(resultTime)
            }

            time.visibility = TextView.VISIBLE
            try_again_button.visibility = View.VISIBLE
            stop_button.visibility = View.INVISIBLE
        }

        try_again_button.setOnClickListener {
            time.visibility = TextView.INVISIBLE
            try_again_button.visibility = View.INVISIBLE
            start_button.visibility = View.VISIBLE
            lights.forEach { it.setImageResource(R.drawable.grey_lights) }
        }
    }
}
