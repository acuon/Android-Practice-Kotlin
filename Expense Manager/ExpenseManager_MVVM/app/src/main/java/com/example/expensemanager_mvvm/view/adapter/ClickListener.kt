package com.example.expensemanager_mvvm.view.adapter

import com.example.expensemanager_mvvm.models.ExpenseTable
import com.example.expensemanager_mvvm.models.IncomeTable

interface IncomeClickListener {

    //fun onClick(money: Money, position:Int)

    fun deleteClick(incomeTable: IncomeTable, position: Int)

    fun editClick(incomeTable: IncomeTable, position: Int)

}

interface ExpenseClickListener {

    fun deleteClick(expenseTable: ExpenseTable, position: Int)

    fun editClick(expenseTable: ExpenseTable, position: Int)

}