package com.example.events.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.events.R
import kotlinx.android.synthetic.main.fragment_main_activity_event_details.*


class MainActivityEventDetailsFragment : Fragment(R.layout.fragment_main_activity_event_details) {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        toTimeAndDate.setOnClickListener() {

            var bundle = Bundle()
            bundle.putString("title", eventTitle.text.toString())
            bundle.putString("description", eventDescription.text.toString())

            navController.navigate(
                R.id.action_mainActivityEventDetailsFragment_to_mainActivityTimeAndDateFragment,
                bundle
            )
        }
    }

}