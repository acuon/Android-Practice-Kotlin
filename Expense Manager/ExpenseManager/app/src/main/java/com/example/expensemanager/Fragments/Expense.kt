package com.example.expensemanager.Fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import kotlinx.android.synthetic.main.fragment_expense.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Expense : Fragment(R.layout.fragment_expense), ClickListener {

    lateinit var adapter: Adapter
    private var listOfExpenses = mutableListOf<Money>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

    }

    private fun setRecyclerView() {
        val dbHandler = activity?.let { DBHelper(it) }
        listOfExpenses = dbHandler?.getAllExpense()!!
        adapter = listOfExpenses?.let { Adapter(it as ArrayList<Money>, this) }!!
        val linearLayoutManager = LinearLayoutManager(context)
        rcvExpense.adapter = adapter
        rcvExpense.layoutManager = linearLayoutManager
    }

//    override fun onClick(money: Money, position: Int) {
//        Toast.makeText(context, "${money.description.toString()} clicked", Toast.LENGTH_LONG).show()
////        activity?.let { DBHelper(it).delete }
//    }

    override fun deleteClick(money: Money, position: Int) {
        val dbHelper = DBHelper(requireContext())
        dbHelper.remove(money, "EXPENSE_TABLE")

        val liveData = LiveData()
        liveData.getExpenseData(dbHelper!!.getExpenseSum())
        liveData.getIncomeData(dbHelper.getIncomeSum())
        liveData.getTotalData(dbHelper!!.getIncomeSum() - dbHelper.getExpenseSum())
        updateUI()

    }

    override fun editClick(money: Money, position: Int) {
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
//                dbHelper.remove(money, "EXPENSE_TABLE")

                money.amount = view.etAmountIncome.text.toString().toDouble()
                money.description = view.etDescriptionIncome.text.toString()
                money.date = view.tvDateSelectorIncome.text.toString()

                dbHelper.edit(money, "EXPENSE_TABLE")

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
        listOfExpenses.clear()
        listOfExpenses.addAll(dbHelper.getAllExpense())
        adapter.notifyDataSetChanged()
    }

}