package com.example.expensemanager.Database

import androidx.lifecycle.MutableLiveData

class LiveData {

    companion object{

        val incomeData = MutableLiveData<String>()
        val expenseData = MutableLiveData<String>()
        val totalData = MutableLiveData<String>()

    }

    fun getIncomeData(income:Double){
        incomeData.value = "$income"
    }

    fun getExpenseData(expense:Double){
        expenseData.value = "$expense"
    }

    fun getTotalData(total:Double){
        totalData.value = "$total"
    }



    fun getIncomeCountData(): MutableLiveData<String> {
        return incomeData
    }
    fun getExpenseCountData(): MutableLiveData<String> {
        return expenseData
    }
    fun getTotalCountData(): MutableLiveData<String> {
        return totalData
    }

}