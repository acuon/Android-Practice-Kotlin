package com.example.events.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.example.events.PreviewActivity
import com.example.events.R
import kotlinx.android.synthetic.main.fragment_main_activity_price_details.*

class MainActivityPriceDetailsFragment : Fragment(R.layout.fragment_main_activity_price_details) {

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var startTime: String
    private lateinit var endTime: String
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getDataFromPreviousFragments()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toPreview.setOnClickListener() {
            val intent = Intent(context, PreviewActivity::class.java)

            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("startTime", startTime)
            intent.putExtra("endTime", endTime)
            intent.putExtra("startDate", startDate)
            intent.putExtra("endDate", endDate)
            intent.putExtra("currency", etCurrency.text.toString())
            intent.putExtra("price", etPrice.text.toString())

            startActivity(intent)
        }

    }

    private fun getDataFromPreviousFragments() {
        arguments?.run {
            title = getString("title")!!
            description = getString("description")!!
            startTime = getString("startTime")!!
            endTime = getString("endTime")!!
            startDate = getString("startDate")!!
            endDate = getString("endDate")!!
        }
    }

}