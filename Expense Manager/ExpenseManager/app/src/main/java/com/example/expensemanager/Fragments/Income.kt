package com.example.expensemanager.Fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.Database.DBHelper
import com.example.expensemanager.Database.LiveData
import com.example.expensemanager.Interface.ClickListener
import com.example.expensemanager.Model.Money
import com.example.expensemanager.R
import com.example.expensemanager.RecyclerViewAdapter.Adapter
import kotlinx.android.synthetic.main.edit_details.view.*
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Income : Fragment(R.layout.fragment_income), ClickListener {

    lateinit var adapter: Adapter
    private var listOfIncomes = mutableListOf<Money>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHandler = activity?.let { DBHelper(it) }
        listOfIncomes = dbHandler?.getAllIncomes()!!
        adapter = Adapter(listOfIncomes as ArrayList<Money>, this)
        val linearLayoutManager = LinearLayoutManager(context)
        rcvIncome.adapter = adapter
        rcvIncome.layoutManager = linearLayoutManager
        tvAmount?.setTextColor(Color.parseColor("#0B2AD5"))

    }

//    override fun onClick(money: Money, position: Int) {
//        Toast.makeText(context, "${money.description.toString()} clicked", Toast.LENGTH_LONG).show()
//    }

    override fun deleteClick(money: Money, position: Int) {

//        val dbHandler = context?.let { DBHelper(it) }
        val dbHandler = DBHelper(requireContext())
        dbHandler.remove(money, "INCOME_TABLE")

        val liveData = LiveData()
        liveData.getIncomeData(dbHandler.getIncomeSum())
        liveData.getExpenseData(dbHandler.getExpenseSum())
        liveData.getTotalData(dbHandler.getIncomeSum() - dbHandler.getExpenseSum())

        updateUI()

    }

    override fun editClick(money: Money, position: Int) {
        Toast.makeText(context, "edit clicked", Toast.LENGTH_SHORT).show()

        val view = layoutInflater.inflate(R.layout.edit_details, null)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)

        alertDialog.setTitle("Edit Details")

        alertDialog.setView(view)
        alertDialog.show()

        view.etAmountIncome.setText(money.amount.toString())
        view.etAmountIncome.requestFocus()

        view.etDescriptionIncome.setText(money.description)

        view.tvDateSelectorIncome.text = money.date

        view.tvDateSelectorIncome.setOnClickListener {

            val cal = Calendar.getInstance()

            val dateSetListener =
                DatePickerDialog.OnDateSetListener { anotherView, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val myFormat = "dd-MM-yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    view.tvDateSelectorIncome.text = sdf.format(cal.time)
                }

            DatePickerDialog(
                view.context,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        view.btnSave.setOnClickListener {

            if (view.etAmountIncome.text.trim().toString().isEmpty()) {

                view.etAmountIncome.error = "cannot be empty"

            } else {

                val dbHelper = DBHelper(requireContext())

                money.amount = view.etAmountIncome.text.toString().toDouble()
                money.description = view.etDescriptionIncome.text.toString()
                money.date = view.tvDateSelectorIncome.text.toString()

                dbHelper.edit(money, "INCOME_TABLE")

                val liveData = LiveData()
                liveData.getExpenseData(dbHelper!!.getExpenseSum())
                liveData.getIncomeData(dbHelper.getIncomeSum())
                liveData.getTotalData(dbHelper!!.getIncomeSum() - dbHelper.getExpenseSum())
                updateUI()

                alertDialog.cancel()

            }

        }

        view.btnCancel.setOnClickListener {
            alertDialog.cancel()
        }

    }

    private fun updateUI() {
        val dbHelper = DBHelper(requireContext())

        listOfIncomes.clear()
        listOfIncomes.addAll(dbHelper.getAllIncomes())
        adapter.notifyDataSetChanged()
    }

}