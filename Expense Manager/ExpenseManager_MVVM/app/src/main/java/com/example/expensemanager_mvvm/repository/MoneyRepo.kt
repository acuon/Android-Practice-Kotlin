package com.example.expensemanager_mvvm.repository

import androidx.lifecycle.LiveData
import com.example.expensemanager_mvvm.models.ExpenseTable
import com.example.expensemanager_mvvm.models.IncomeTable
import com.example.expensemanager_mvvm.models.MoneyDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyRepo(private val moneyDAO: MoneyDAO) {

    fun addToIncome(income: IncomeTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.insertToIncome(income)
        }
    }

    fun addToExpense(expense: ExpenseTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.insertToExpense(expense)
        }
    }

    fun getIncomes(): LiveData<MutableList<IncomeTable>> {
        return moneyDAO.getIncomes()
    }

    fun getExpenses(): LiveData<MutableList<ExpenseTable>> {
        return moneyDAO.getExpenses()
    }


    fun updateIncome(income: IncomeTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.updateIncome(income)
        }
    }

    fun updateExpense(expense: ExpenseTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.updateExpense(expense)
        }
    }


    fun deleteFromIncome(income: IncomeTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.deleteFromIncome(income)
        }
    }

    fun deleteFromExpense(expense: ExpenseTable) {
        CoroutineScope(Dispatchers.IO).launch {
            moneyDAO.deleteFromExpense(expense)
        }
    }

    fun getAllIncomeSum(): LiveData<Double> {
        return moneyDAO.getAllIncomeSum()
    }

    fun getAllExpenseSum(): LiveData<Double> {
        return moneyDAO.getAllExpenseSum()
    }

}