package com.example.events

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        intent?.run {
            tvTitle.text = getStringExtra("title")
            tvDescription.text = getStringExtra("description")
            tvStartDate.text = getStringExtra("startDate")
            tvEndDate.text = getStringExtra("endDate")
            tvStartTime.text = getStringExtra("startTime")
            tvEndTime.text = getStringExtra("endTime")
            val price = "${getStringExtra("currency")} ${getStringExtra("price")}"
            tvPrice.text = price
        }

    }


}
