package com.example.expensemanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.expensemanager.Database.DBHelper
import com.example.expensemanager.Database.LiveData
import com.example.expensemanager.R
import kotlinx.android.synthetic.main.fragment_balance.*

class Balance : Fragment(R.layout.fragment_balance) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHandler = activity?.let { DBHelper(it) }


        val totalIncome = dbHandler?.getIncomeSum().toString().toDouble()
        val totalExpense = dbHandler?.getExpenseSum().toString().toDouble()
        val total = totalIncome - totalExpense

        tvOverallIncome.text = String.format("%.2f", totalIncome)
        tvOverallExpense.text = String.format("%.2f", totalExpense)
        tvOverallTotal.text = String.format("%.2f", total)

        val liveData = LiveData()
        liveData.getIncomeCountData().observe(viewLifecycleOwner, Observer {
            tvOverallIncome.text = it
        })

        liveData.getExpenseCountData().observe(viewLifecycleOwner, Observer {
            tvOverallExpense.text = it
        })

        liveData.getTotalCountData().observe(viewLifecycleOwner, Observer {
            tvOverallTotal.text = it
        })

    }

}