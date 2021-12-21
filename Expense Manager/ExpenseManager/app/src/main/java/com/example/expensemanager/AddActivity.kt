package com.example.expensemanager

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.Database.DBHelper
import com.example.expensemanager.Fragments.Income
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {

    private val dbHandler = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        relative.setOnClickListener {
            this.finish()
        }

        btnSubmit.setOnClickListener {

            val tableName = spinnerIncomeExpense.selectedItem.toString()
            val amount = etAmount.text.toString().toDouble()
            val description = etDescription.text.toString()
            val date = tvDateSelector.text.toString()

            when (tableName) {
                "Income" -> {
                    dbHandler.insertIncome(amount, date, description)
                    Income().adapter.notifyDataSetChanged()
                    //AddActivity().finish()
                    //this.finish()
                }
                "Expense" -> {
                    dbHandler.insertExpense(amount, date, description)
                    Income().adapter.notifyDataSetChanged()
                    //this.finish()
                }
            }



//            Toast.makeText(
//                baseContext,
//                "Successfully added in ${spinnerIncomeExpense.selectedItem.toString()}s",
//                Toast.LENGTH_LONG
//            ).show()

        }

        //for date picker
        tvDateSelector.text = SimpleDateFormat("dd-MM-yyyy").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd-MM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                tvDateSelector.text = sdf.format(cal.time)

            }

        tvDateSelector.setOnClickListener {
            DatePickerDialog(
                this@AddActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        //date picker end

    }


}