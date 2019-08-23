package com.fakhar.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val START_TIME_IN_MILLIS : Long = 600000

    lateinit var mtvCountDown : TextView
    lateinit var mButtonStatPuase : Button
    lateinit var mButtonReset : Button

    lateinit var mCountDownTimer: CountDownTimer
     var mTimerRunning : Boolean =false

    var mTIME_LEFT_IN_MILLIS : Long = START_TIME_IN_MILLIS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mtvCountDown = tv_countdown
        mButtonStatPuase = btn_start
        mButtonReset = btn_reset

        mButtonStatPuase.setOnClickListener(View.OnClickListener {

            if(mTimerRunning)
            {
                Pausetimer()
            }
            else
                StartTimer()

        })

        mButtonReset.setOnClickListener(View.OnClickListener {
            ResetTimer()
        })

        updateCountDownText()
    }

    fun Pausetimer()
    {
        mCountDownTimer.cancel()
        mTimerRunning = false
        mButtonStatPuase.text = "Start"
        mButtonReset.visibility = View.VISIBLE
    }

    fun StartTimer()
    {
        mCountDownTimer = object : CountDownTimer(mTIME_LEFT_IN_MILLIS , 1000)
        {
            override fun onFinish() {
                mTimerRunning = false
                mButtonStatPuase.text = "Start"
                mButtonReset.visibility = View.VISIBLE
                mButtonStatPuase.visibility = View.INVISIBLE
            }

            override fun onTick(p0: Long) {
                mTIME_LEFT_IN_MILLIS = p0
                updateCountDownText()
            }

        }.start()

        mTimerRunning = true
        mButtonStatPuase.text = "pause"
        mButtonReset.visibility = View.INVISIBLE
    }

    private fun updateCountDownText() {
        var minutes : Int = ((mTIME_LEFT_IN_MILLIS / 1000)/ 60).toInt()
        var seconds : Int = ((mTIME_LEFT_IN_MILLIS / 1000)% 60).toInt()

        var timeLeftFormat : String = String.format(Locale.getDefault(),"%02d:%02d" , minutes , seconds)

        mtvCountDown.text = timeLeftFormat
    }

    fun ResetTimer()
    {
        mTIME_LEFT_IN_MILLIS = START_TIME_IN_MILLIS
        updateCountDownText()
        mButtonReset.visibility = View.INVISIBLE
        mButtonStatPuase.visibility = View.VISIBLE
    }
    
}
