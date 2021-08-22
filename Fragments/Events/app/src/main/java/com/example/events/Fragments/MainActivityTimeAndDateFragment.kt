package com.example.events.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.events.R
import kotlinx.android.synthetic.main.fragment_main_activity_time_and_date.*

class MainActivityTimeAndDateFragment : Fragment(R.layout.fragment_main_activity_time_and_date) {

    private lateinit var title: String
    private lateinit var description: String

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gedDataFromEventDetails()
    }

    private fun gedDataFromEventDetails() {
        arguments?.run {
            title = getString("title")!!
            description = getString("description")!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)


        toPriceDetail.setOnClickListener() {

            var bundle = Bundle()

            bundle.putString("title", title)
            bundle.putString("description", description)
            bundle.putString("startTime", startTime.text.toString())
            bundle.putString("endTime", endTime.text.toString())
            bundle.putString("startDate", startDate.text.toString())
            bundle.putString("endDate", endDate.text.toString())

            navController.navigate(
                R.id.action_mainActivityTimeAndDateFragment_to_mainActivityPriceDetailsFragment,
                bundle
            )
        }

    }


}