package com.example.expensemanager.Interface

import com.example.expensemanager.Model.Money

interface ClickListener {

    //fun onClick(money: Money, position:Int)

    fun deleteClick(money: Money, position: Int)

    fun editClick(money: Money, position: Int)

}